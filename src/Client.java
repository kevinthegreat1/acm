import acm.program.GraphicsProgram;
import util.Vec3d;

import java.awt.*;
import java.awt.event.MouseEvent;

public class Client extends GraphicsProgram {
    private final Renderer renderer;
    private boolean running = true;
    private int halfWidth;
    private int halfHeight;
    private Point prevMouse;

    public Client() {
        this.renderer = new Renderer(this);
    }

    public int getHalfWidth() {
        return halfWidth;
    }

    public int getHalfHeight() {
        return halfHeight;
    }

    public void run() {
        renderer.addVertex(new Vec3d(1, 1, -1));
        renderer.addVertex(new Vec3d(-1, 1, -1));
        renderer.addVertex(new Vec3d(-1, -1, -1));
        renderer.addVertex(new Vec3d(1, -1, -1));
        renderer.addVertex(new Vec3d(1, 1, -3));
        renderer.addVertex(new Vec3d(-1, 1, -3));
        renderer.addVertex(new Vec3d(-1, -1, -3));
        renderer.addVertex(new Vec3d(1, -1, -3));
        renderer.addEdge(0 + 4, 1 + 4);
        renderer.addEdge(1 + 4, 2 + 4);
        renderer.addEdge(2 + 4, 3 + 4);
        renderer.addEdge(3 + 4, 0 + 4);
        renderer.addEdge(4 + 4, 5 + 4);
        renderer.addEdge(5 + 4, 6 + 4);
        renderer.addEdge(6 + 4, 7 + 4);
        renderer.addEdge(7 + 4, 4 + 4);
        renderer.addEdge(0 + 4, 4 + 4);
        renderer.addEdge(1 + 4, 5 + 4);
        renderer.addEdge(2 + 4, 6 + 4);
        renderer.addEdge(3 + 4, 7 + 4);
        renderer.getCamera().addPosition(0, 0, 0.1);
        while (running) {
            update();
            renderer.getCamera().addYaw(0.5);
            pause(10);
        }
    }

    public void update() {
        halfWidth = getWidth() >> 1;
        halfHeight = getHeight() >> 1;
        renderer.update();
    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {
        if (prevMouse != null) {
            Point mouse = getMousePosition();
            mouse.translate(-prevMouse.x, -prevMouse.y);
            renderer.getCamera().addYaw(mouse.x);
            renderer.getCamera().addPitch(-mouse.y);
        }
        prevMouse = getMousePosition();
        System.out.println(prevMouse);
    }
}
