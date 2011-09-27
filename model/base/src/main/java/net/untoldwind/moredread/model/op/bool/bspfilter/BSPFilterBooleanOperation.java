package net.untoldwind.moredread.model.op.bool.bspfilter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.untoldwind.moredread.model.mesh.IMesh;
import net.untoldwind.moredread.model.mesh.IVertex;
import net.untoldwind.moredread.model.mesh.PolyMesh;
import net.untoldwind.moredread.model.mesh.TriangleFace;
import net.untoldwind.moredread.model.mesh.TriangleMesh;
import net.untoldwind.moredread.model.mesh.Vertex;
import net.untoldwind.moredread.model.op.IBooleanOperation;
import net.untoldwind.moredread.model.op.utils.UnitRescale;
import net.untoldwind.moredread.model.op.utils.VertexSet;

public class BSPFilterBooleanOperation implements IBooleanOperation {

	@Override
	public IMesh performBoolean(final BoolOperation operation, final IMesh inA,
			final IMesh inB) {
		TriangleMesh meshA = inA.toTriangleMesh();
		TriangleMesh meshB = inB.toTriangleMesh();
		final boolean invertMeshA = (operation == BoolOperation.UNION);
		final boolean invertMeshB = (operation != BoolOperation.INTERSECTION);
		final boolean invertResult = (operation == BoolOperation.UNION);

		final VertexSet vertexSet = new VertexSet();
		final Map<BoolVertex.IBoolIndex, Integer> vertexMapA = new HashMap<BoolVertex.IBoolIndex, Integer>();
		final Map<BoolVertex.IBoolIndex, Integer> vertexMapB = new HashMap<BoolVertex.IBoolIndex, Integer>();

		if (invertMeshA) {
			meshA = meshA.invert();
		}

		if (invertMeshB) {
			meshB = meshB.invert();
		}
		final UnitRescale unitRescale = new UnitRescale(meshA, meshB);
		unitRescale.rescaleInput(meshA);
		unitRescale.rescaleInput(meshB);

		final List<TriangleFace> facesA = new ArrayList<TriangleFace>(
				meshA.getFaces());
		final List<TriangleFace> facesB = new ArrayList<TriangleFace>(
				meshB.getFaces());
		final BSPTree bspA = new BSPTree();
		bspA.addMesh(meshA);
		final BSPTree bspB = new BSPTree();
		bspB.addMesh(meshB);

		final List<List<Integer>> faces = new ArrayList<List<Integer>>();
		PolyMesh result = new PolyMesh();

		bspFilter(result, faces, vertexSet, bspA, facesB, vertexMapB);
		bspFilter(result, faces, vertexSet, bspB, facesA, vertexMapA);
		mergeMidpoints(result, faces);

		for (final List<Integer> face : faces) {
			final int[] indices = new int[face.size()];

			for (int i = 0; i < indices.length; i++) {
				indices[i] = face.get(i);
			}

			result.addFace(indices);
		}
		if (invertResult) {
			result = result.invert();
		}
		unitRescale.rescaleOutput(result);

		return result;
	}

	private void bspFilter(final PolyMesh result,
			final List<List<Integer>> faces, final VertexSet vertexSet,
			final BSPTree filter, final List<TriangleFace> source,
			final Map<BoolVertex.IBoolIndex, Integer> vertexMap) {
		for (final TriangleFace face : source) {
			final IVertex[] verticies = face.getVertexArray();

			final List<BoolFace> inFaces = filter.testTriangle(verticies[0],
					verticies[1], verticies[2]);

			for (final BoolFace inFace : inFaces) {
				final BoolVertex[] vertices = inFace.getVertices();
				if (vertices.length < 3) {
					continue;
				}

				final List<Integer> indices = new ArrayList<Integer>(
						verticies.length);

				for (int i = 0; i < vertices.length; i++) {
					indices.add(transferredIndex(result, vertexSet,
							vertices[i], vertexMap));
				}
				faces.add(indices);
			}
		}
	}

	private int transferredIndex(final PolyMesh result,
			final VertexSet vertexSet, final BoolVertex vertex,
			final Map<BoolVertex.IBoolIndex, Integer> vertexMap) {
		Integer index = vertexMap.get(vertex.getIndex());

		if (index == null) {
			IVertex newVertex = vertexSet.findVertex(vertex.getPoint());

			if (newVertex == null) {
				newVertex = result.addVertex(vertex.getPoint());
				vertexSet.addVertex(newVertex);
			}
			index = newVertex.getIndex();
			vertexMap.put(vertex.getIndex(), index);
		}
		return index;
	}

	private void mergeMidpoints(final PolyMesh result,
			final List<List<Integer>> faces) {
		for (final Vertex vertex : result.getVertices()) {
			for (final List<Integer> face : faces) {
				IVertex prev = result.getVertex(face.get(face.size() - 1));
				for (int i = 0; i < face.size(); i++) {
					final IVertex next = result.getVertex(face.get(i));

					if (vertex.getIndex() != prev.getIndex()
							&& vertex.getIndex() != next.getIndex()
							&& MathUtils.isOnLine(vertex.getPoint(),
									prev.getPoint(), next.getPoint())) {
						face.add(i, vertex.getIndex());
						prev = vertex;
					} else {
						prev = next;
					}
				}
			}
		}
	}
}
