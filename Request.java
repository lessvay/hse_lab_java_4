package org.example;

public class Request {
    final int fromFloor;
    final int toFloor;

    public Request(int fromFloor, int toFloor) {
        this.fromFloor = fromFloor;
        this.toFloor = toFloor;
    }

    public int GetfromFloor(){
        return fromFloor;
    }
    public int GettoFloor()
    {
        return toFloor;
    }
}

