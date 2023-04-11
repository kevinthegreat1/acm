import util.Vec3d;

public class Camera {
    private Vec3d position;
    private Vec3d imagePlane;
    private double yaw = 0;
    private double pitch = 0;
    private double roll = 0;

    public Camera() {
        this(new Vec3d(0, 0, 1), new Vec3d(0, 0, -1));
    }

    public Camera(Vec3d position) {
        this(position, new Vec3d(0, 0, -1));
    }

    public Camera(Vec3d position, Vec3d imagePlane) {
        this.position = position;
        this.imagePlane = imagePlane;
    }

    public Vec3d getPosition() {
        return position;
    }

    public void setPosition(Vec3d position) {
        this.position = position;
    }

    public Vec3d getImagePlane() {
        return imagePlane;
    }

    public void setImagePlane(Vec3d imagePlane) {
        this.imagePlane = imagePlane;
    }

    public double getYaw() {
        return yaw;
    }

    public void setYaw(double yaw) {
        this.yaw = yaw;
    }

    public void addYaw(double yaw) {
        this.yaw += yaw;
    }

    public double getPitch() {
        return pitch;
    }

    public void setPitch(double pitch) {
        this.pitch = pitch;
    }

    public void addPitch(double pitch) {
        this.pitch += pitch;
    }

    public double getRoll() {
        return roll;
    }

    public void setRoll(double roll) {
        this.roll = roll;
    }

    public void addRoll(double roll) {
        this.roll += roll;
    }
}
