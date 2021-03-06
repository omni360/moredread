package net.untoldwind.moredread.model.generator;

import java.util.List;

import net.untoldwind.moredread.model.math.Vector3;
import net.untoldwind.moredread.model.mesh.TriangleMesh;

public class OctahedronMeshGenerator extends AbstractCenterSizeGenerator {

	public OctahedronMeshGenerator() {
		super(new Vector3(), 1.0f);
	}

	public OctahedronMeshGenerator(final Vector3 center, final float size) {
		super(center, size);
	}

	@Override
	public String getName() {
		return "Octahedron";
	}

	@Override
	public TriangleMesh generateGeometry(final List<IGeneratorInput> generatorInputs) {
		final TriangleMesh mesh = new TriangleMesh();

		mesh.addVertex(new Vector3(size, 0, 0).addLocal(center));
		mesh.addVertex(new Vector3(-size, 0, 0).addLocal(center));
		mesh.addVertex(new Vector3(0, size, 0).addLocal(center));
		mesh.addVertex(new Vector3(0, -size, 0).addLocal(center));
		mesh.addVertex(new Vector3(0, 0, size).addLocal(center));
		mesh.addVertex(new Vector3(0, 0, -size).addLocal(center));

		mesh.addFace(4, 0, 2);
		mesh.addFace(4, 2, 1);
		mesh.addFace(4, 1, 3);
		mesh.addFace(4, 3, 0);
		mesh.addFace(5, 2, 0);
		mesh.addFace(5, 1, 2);
		mesh.addFace(5, 3, 1);
		mesh.addFace(5, 0, 3);

		return mesh;
	}

}
