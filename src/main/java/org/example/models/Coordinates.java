package org.example.models;

/**
 * @param x private final Entity entity;
 */
public record Coordinates(int x, int y) {
    //private int position;
    //private boolean busy;

    //this.entity = Entity.createEntity(type.getClassEntity());
    //        this.x = calculateX(widthMap, numPosition);
    //        this.y = calculateY(widthMap, numPosition);
    //        this.position = numPosition;
    //        this.busy = false;

//    public Coordinates(Class <?> entityClass, int widthMap, int numPosition) {
//        this.entity =Entity.createEntity(entityClass);
//        this.x = calculateX(widthMap, numPosition);
//        this.y = calculateY(widthMap, numPosition);
//        this.position = numPosition;
//        this.busy = false;
//    }

//    private int calculateX(int widthMap, int numPosition) {
//        if (numPosition % widthMap == 0) {
//            return widthMap;
//        }
//
//        return numPosition % widthMap;
//
//    }

//    private int calculateY(int widthMap, int numPosition) {
//        if (numPosition % widthMap == 0) {
//            return numPosition / widthMap;
//        }
//
//        return numPosition / widthMap + 1;
//
//    }

//    //public Entity getEntity() {
//        return entity;
//    }

//    public int getPosition() {
//        return position;
//    }
//
//    public void setPosition(int position) {
//        int widthMap = GameMap.getInstance().getWidth();
//        this.position = position;
//        this.x = calculateX(widthMap, position);
//        this.y = calculateY(widthMap, position);
//    }
//
//    public boolean isBusy() {
//        return busy;
//    }
//
//    public void setBusy(boolean busy) {
//        this.busy = busy;
//    }
//
//    public void setBusy() {
//        this.busy = true;
//    }
}
