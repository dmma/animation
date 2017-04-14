package ua.dp.dmma;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * @author dmma
 */
public class Animation {
    public String[] animate(int speed, String init) {
        List<String> result = new ArrayList<String>();
        List<List<Point>> visibleScreen = new ArrayList<List<Point>>(init.length());
        for (int i = 0; i < init.length(); i++) {
            char symbol = init.charAt(i);
            Point point = new Point(Direction.getDirectionByCode(String.valueOf(symbol)), -1);

            visibleScreen.add(i, new ArrayList<Point>(Collections.singletonList(point)));
        }

        boolean emptyScreen = false;
        int currentIteration = 0;

        while (!emptyScreen) {
            result.add(getVisibleScreenStringView(visibleScreen));
            emptyScreen = true;
            for (int i = 0; i < visibleScreen.size(); i++) {
                List<Point> points = visibleScreen.get(i);
                for (Iterator<Point> pointIterator = points.iterator(); pointIterator.hasNext(); ) {
                    Point point = pointIterator.next();
                    if (Direction.E != point.direction && point.getLastIterationNumber() < currentIteration) {
                        emptyScreen = false;
                        pointIterator.remove();
                        if (Direction.R == point.getDirection()) {
                            if (i + speed < visibleScreen.size()) {
                                List<Point> newDest = visibleScreen.get(i + speed);
                                point.setLastIterationNumber(currentIteration);
                                newDest.add(point);
                            }
                        } else if (Direction.L == point.getDirection()) {
                            if (i - speed >= 0) {
                                List<Point> newDest = visibleScreen.get(i - speed);
                                point.setLastIterationNumber(currentIteration);
                                newDest.add(point);
                            }
                        }
                    }
                }
            }
            currentIteration++;
        }

        return result.toArray(new String[result.size()]);
    }

    private String getVisibleScreenStringView(List<List<Point>> visibleScreen) {
        StringBuilder sb = new StringBuilder();
        for (List<Point> points : visibleScreen) {
            boolean isFilled = false;
            for (Point point : points) {
                if (Direction.L == point.direction || Direction.R == point.getDirection()) {
                    isFilled = true;
                }
            }
            sb.append(isFilled ? "X" : ".");
        }
        return sb.toString();
    }

    class Point {
        private Direction direction;
        private int lastIterationNumber;

        public Point(Direction direction, int lastIterationNumber) {
            this.direction = direction;
            this.lastIterationNumber = lastIterationNumber;
        }

        public Direction getDirection() {
            return direction;
        }

        public int getLastIterationNumber() {
            return lastIterationNumber;
        }

        public void setLastIterationNumber(int lastIterationNumber) {
            this.lastIterationNumber = lastIterationNumber;
        }
    }

    enum Direction {
        L("L"), R("R"), E(".");

        private String code;

        Direction(String code) {
            this.code = code;
        }

        public static Direction getDirectionByCode(String code) {
            for (Direction direction : values()) {
                if (direction.code.equals(code)) {
                    return direction;
                }
            }
            throw new IllegalArgumentException("No enum constant " + Direction.class.getName());
        }
    }
}
