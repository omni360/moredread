package net.untoldwind.moredread.model.mesh;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.untoldwind.moredread.model.math.Vector3;
import net.untoldwind.moredread.model.state.IStateReader;
import net.untoldwind.moredread.model.state.IStateWriter;
import net.untoldwind.moredread.model.transform.ITransformation;

public class TriangleFace extends Face<TriangleFaceId, TriangleFace> {
	private final Vertex[] vertices;
	private final Edge[] edges;

	TriangleFace(final TriangleMesh owner, final TriangleFaceId index,
			final Vertex[] vertices, final Edge[] edges) {
		super(owner, index);

		this.vertices = vertices;
		this.edges = edges;

		for (final Vertex vertex : vertices) {
			vertex.getFaces().add(this);
		}

		for (final Edge edge : edges) {
			edge.getFaces().add(this);
		}
	}

	@Override
	public int getVertexCount() {
		return 3;
	}

	public Vertex getVertex(final int index) {
		return vertices[index];
	}

	@Override
	public List<Vertex> getVertices() {
		return Arrays.asList(vertices);
	}

	public Vertex[] getVertexArray() {
		return vertices;
	}

	@Override
	public IEdge getEdge(final EdgeId edgeIndex) {
		for (final Edge edge : edges) {
			if (edge.getIndex().equals(edgeIndex)) {
				return edge;
			}
		}
		return null;
	}

	@Override
	public List<Edge> getEdges() {
		return Arrays.asList(edges);
	}

	@Override
	public int[] getPolygonContourCounts() {
		return new int[] { 1 };
	}

	@Override
	public int[] getPolygonStripCounts() {
		return new int[] { 3 };
	}

	@Override
	public boolean isClosed() {
		return true;
	}

	@Override
	public Vector3 calculateCenter() {
		final Vector3 center = new Vector3(0, 0, 0);

		for (final Vertex vertex : vertices) {
			center.addLocal(vertex.getPoint());
		}

		center.divideLocal(3);

		return center;
	}

	@Override
	public Vector3 calculateMeanNormal() {
		final Vector3 v1 = vertices[1].getPoint().subtract(
				vertices[0].getPoint());
		final Vector3 v2 = vertices[2].getPoint().subtract(
				vertices[0].getPoint());
		final Vector3 normal = v1.cross(v2);
		final float len = normal.length();

		if (len == 0f) {
			normal.set(0, 0, 1);
		} else {
			normal.divideLocal(len);
		}

		return normal;
	}

	@Override
	void remove() {
		for (final Edge edge : edges) {
			edge.getFaces().remove(this);
		}
		for (final Vertex vertex : vertices) {
			vertex.getFaces().remove(this);
		}
	}

	@Override
	public IPolygon transform(final ITransformation transformation) {
		final List<IPoint> new_vertices = new ArrayList<IPoint>(vertices.length);

		for (final IPoint point : vertices) {
			new_vertices.add(point.transform(transformation));
		}

		return new Polygon(new_vertices, getPolygonStripCounts(),
				getPolygonContourCounts(), true);
	}

	@Override
	public void readState(final IStateReader reader) throws IOException {
		// TODO Auto-generated method stub

	}

	@Override
	public void writeState(final IStateWriter writer) throws IOException {
		for (final Vertex vertex : vertices) {
			writer.writeInt("vertexIndex", vertex.getIndex());
		}
	}

	@Override
	public String toString() {
		final StringBuilder builder = new StringBuilder();
		builder.append("TriangleFace [edges=");
		builder.append(Arrays.toString(edges));
		builder.append(", vertices=");
		builder.append(Arrays.toString(vertices));
		builder.append("]");
		return builder.toString();
	}

}
