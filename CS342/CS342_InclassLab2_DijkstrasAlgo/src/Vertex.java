public class Vertex {
    Vertex next;
    int id;
    int distance;

    public Integer getData() {
        return id;
    }
    public void setData(Integer data) {
        this.id = data;
    }
    public Vertex getNext() {
        return next;
    }
    public void setNext(Vertex next) {
        this.next = next;
    }
    public Integer getDistance() {return distance;}
    public void setDistance(int distance) {this.distance = distance;}
}
