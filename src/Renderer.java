import acm.graphics.GLine;
import util.Pair;
import util.Vec2d;
import util.Vec3d;

import java.util.ArrayList;
import java.util.List;

public class Renderer {
    private final Client client;
    private final Camera camera;
    private static final Vec3d ORIGIN = new Vec3d(0, 0, 0);
    private static final Vec3d X_AXIS = new Vec3d(Double.MAX_VALUE, 0, 0);
    private static final Vec3d Y_AXIS = new Vec3d(0, Double.MAX_VALUE, 0);
    private static final Vec3d Z_AXIS = new Vec3d(0, 0, Double.MAX_VALUE);
    private final List<Vec3d> vertices = new ArrayList<>();
    private final List<Pair<Pair<Integer, Integer>, GLine>> edges = new ArrayList<>();

    public Renderer(Client client) {
        this.client = client;
        this.camera = new Camera();
        vertices.add(ORIGIN);
        vertices.add(X_AXIS);
        vertices.add(Y_AXIS);
        vertices.add(Z_AXIS);
        GLine x_axis = projectEdge(0, 1);
        x_axis.setColor(java.awt.Color.RED);
        addLine(0, 1, x_axis);
        GLine y_axis = projectEdge(0, 2);
        y_axis.setColor(java.awt.Color.GREEN);
        addLine(0, 2, y_axis);
        GLine z_axis = projectEdge(0, 3);
        z_axis.setColor(java.awt.Color.BLUE);
        addLine(0, 3, z_axis);
    }

    public Camera getCamera() {
        return camera;
    }

    public boolean addVertex(Vec3d vertex) {
        return vertices.add(vertex);
    }

    public boolean addEdge(int vertex1, int vertex2) {
        if (vertex1 < 0 || vertex1 >= vertices.size() || vertex2 < 0 || vertex2 >= vertices.size()) {
            throw new IllegalArgumentException("Invalid vertex index");
        }
        GLine line = projectEdge(vertex1, vertex2);
        return addLine(vertex1, vertex2, line);
    }

    public boolean addLine(int vertex1, int vertex2, GLine line) {
        client.add(line);
        return edges.add(new Pair<>(new Pair<>(vertex1, vertex2), line));
    }

    public GLine projectEdge(int vertex1, int vertex2) {
        Pair<Vec2d, Boolean> projected1 = project(vertex1);
        Pair<Vec2d, Boolean> projected2 = project(vertex2);
        return new GLine(projected1.left().x(), projected1.left().y(), projected2.left().x(), projected2.left().y());
    }

    public Pair<Vec2d, Boolean> project(int index) {
        Vec3d vec3d = vertices.get(index);
        Vec3d transformed = vec3d.subtract(camera.getPosition()).rotateX(camera.getPitch()).rotateY(camera.getYaw()).rotateZ(camera.getRoll());
        double x = camera.getImagePlane().z() / transformed.z() * transformed.x() + camera.getImagePlane().x();
        double y = camera.getImagePlane().z() / transformed.z() * transformed.y() + camera.getImagePlane().y();
        return new Pair<>(new Vec2d(client.getHalfHeight() * (x) + client.getHalfWidth(), client.getHalfHeight() * (y) + client.getHalfHeight()), transformed.z() <= 0);
    }

    public void update() {
        for (Pair<Pair<Integer, Integer>, GLine> edge : edges) {
            Pair<Vec2d, Boolean> projected1 = project(edge.left().left());
            Pair<Vec2d, Boolean> projected2 = project(edge.left().right());
            edge.right().setVisible(projected1.right() && projected2.right());
            edge.right().setStartPoint(projected1.left().x(), projected1.left().y());
            edge.right().setEndPoint(projected2.left().x(), projected2.left().y());
        }
    }
}
