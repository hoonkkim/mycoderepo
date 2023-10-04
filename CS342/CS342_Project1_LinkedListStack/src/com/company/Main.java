package com.company;

public class Main {

    public static void main(String[] args) {

        // Initialize new stack to use for algorithm
        LinkedStack MyStack = new LinkedStack();
        // Set some default flag values
        // Solved = false to begin
        boolean solved = false;

        // newX & newY track newly added Queen's coordinates
        // oldX & oldY track previous Queens' coordinates
        Integer newX = 1;
        Integer newY = 1;
        Integer oldX = 0;
        Integer oldY = -10;

        // Use newX & newY to place first Queen into (1,1)
        MyStack.push(newX, newY);
        System.out.println("Start with: (" + newX + ", " + newY + ")");

        // increment X by 1 so loop starts from (2, 1)
        // all values in row 1 will always be conflicted with (1, 1)
        newX = newX + 1;

        // We will continue to place Queens unless we solve the algorithm and the board is not empty (the latter should always be true unless there's a bug)

        // newY cannot exceed 8 as it will fall off the chessboard
        while (newY < 9 & solved == false) {
            // add new Queen in new X and new Y
            MyStack.push(newX, newY);

            // Set the Queencounter variable to be the size of the stack. This will be used for the itemAt() method to compute conflicts
            int queencounter = MyStack.size();
            // index starts at 2 because we never need to check top item with itemat because that's the queen we just added
            int index = 2;
            // default conflicts is 0. it gets incremented if there is a conflict
            int conflictcounter = 0;

            while (index < queencounter + 1) {
                // check value always begins at 2 and ends at queencount (no need to check against itself)
                // use ItemAt to iterate through the existing Queens in the stack
                oldX = MyStack.itemAt(index).getX();
                oldY = MyStack.itemAt(index).getY();

                // Conflict Check
                // X equivalence means there are two queens in the same row
                // Y equivalence means there are two queens in the same column
                // Sum & diff equivalence means there are two queens in the same diagonal path from each other
                // If ANY of these are true, there is a conflict
                if (MyStack.peek().getX().equals(oldX)
                        || MyStack.peek().getY().equals(oldY)
                        || MyStack.peek().getSum().equals(oldX + oldY)
                        || MyStack.peek().getDiff().equals(oldX - oldY)) {
                    // Add 1 to conflict counter to we fall into conflict resolution
                    conflictcounter++;
                }
                // Check against all queens in stack
                index++;
            }
            // If there is a conflict, let the user know
            System.out.println(conflictcounter + " Conflict(s) for top: (" + newX + ", " + newY + ")");

            // Enter conflict resolution/backwards popping
            if (conflictcounter > 0) {
                // there was a conflict! pop it!
                // Debugging text
                // System.out.println(" Popping (" + MyStack.peek().getX() + ", " + MyStack.peek().getY() + ")");
                MyStack.pop();

                // Debugging text
                // System.out.println(" Top is now (" + MyStack.peek().getX() + ", " + MyStack.peek().getY() + ")");
                // was the most recent attempt at Y == 8?
                // if No then just try the next one to the right
                if (newY != 8)
                {
                    // System.out.println("Setting newY to be "+(newY+1));
                    newY = newY + 1;
                }
                else
                {
                    // if Yes then pop the previous one Until we are not at column 8.
                    while (MyStack.peek().getY() == 8) {
                        MyStack.pop();

                    }
                    // now that we have removed all column Y Queens, we must remove one more and move the attempt 1 column to the right
                    // first set and move the target square 1 to the right (Y = Y+1)
                    newX = MyStack.peek().getX();
                    newY = MyStack.peek().getY() + 1;
                    // Debugging Text
                    // System.out.println(" Top is now (" + MyStack.peek().getX() + ", " + MyStack.peek().getY() + ")");
                    // System.out.println(" New X,Y to be tried is (" + newX + ", " + newY + ")");
                    // Kick out the Queen before the one that wound up at Y == 8
                    MyStack.pop();
                }
            }

            // if there are no conflicts, keep the newest queen and move to the next row
            else {
                System.out.println("Added New Top: (" + newX + ", " + newY + ")");
                newX = newX + 1;
                newY = 1;

                System.out.println("Stack size is currently " + MyStack.size());
                // if the last queen was the 8th on the board, we have solved the problem so flip the solved switch to exit the loop.
                if (MyStack.size() == 8) {
                    solved = true;
                }
            }
        }

//         Solution Outputter. Starts with deepest item in stack and moves to the top.
        // boardX boardY are used to create each cell in the 8X8 board while crawling to the right and down sequentially
        int boardX = 1;
        int boardY = 1;
        // String line aggregates each row's input (a blank or a Q) until we get to the end of the row, then outputs it.
        String Line = new String();
        // helper into to get through the stack from top to bottom.
        int boardindex = MyStack.size();
        // start with top border of chess board.
        System.out.println("+---+---+---+---+---+---+---+---+");

        // loop 8 times for X and Y respectively
        while (boardX<9) {
            // Leftmost border of every row
            Line = "|";
            while (boardY < 9)
            {
                // if board coordinates equal stack item coordinates, place a Queen. otherwise, place a blank.
                // whenever a queen is place, move 1 position up the stack towards top.
                if (boardindex > 0 && boardX == MyStack.itemAt(boardindex).getX() && boardY == MyStack.itemAt(boardindex).getY()) {
                    Line=Line+(" Q |");
                    boardindex--;
                } else {
                    Line = Line+("   |");
                }
                // increment board Y position after every cell build
                boardY++;
            }
            // row is complete - print out row content
            System.out.println(Line);
            // place horizontal divider after row
            System.out.println("+---+---+---+---+---+---+---+---+");
            // increment row number by 1
            boardX++;
            // reset column counter to 1 so we begin from the leftmost cell again
            boardY = 1;
        }

    }

}

