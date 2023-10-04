package MenuTools;

import Menus.Menu;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class menuRecommendationSorter extends Thread {
    // This class implements thread because I anticipate with higher Menu volume
    // and additional sort methods being added, the amount of time it requires may
    // bring it into conflict with user input timings.
    ArrayList<ArrayList<Menu>> sortedArrayListArrayList = new ArrayList<ArrayList<Menu>>();

    public void run(ArrayList<Menu> menuArrayList) {
//      System.out.println("We are In the Run Method");
        // Index Guide:
        // 0 Score Desc, 1-2 Cost Asc-Desc, 3-4 Frequency Asc-Desc
        // 5-6 Recency Asc-Desc, 7-8 Name Asc-Desc
        sortedArrayListArrayList.add(this.sortMenusByScore(menuArrayList));
        sortedArrayListArrayList.add(this.sortMenusByCostAsc(menuArrayList));
        sortedArrayListArrayList.add(this.sortMenusByCostDesc(menuArrayList));
        sortedArrayListArrayList.add(this.sortMenusByFrequencyAsc(menuArrayList));
        sortedArrayListArrayList.add(this.sortMenusByFrequencyDesc(menuArrayList));
        sortedArrayListArrayList.add(this.sortMenusByRecencyAsc(menuArrayList));
        sortedArrayListArrayList.add(this.sortMenusByRecencyDesc(menuArrayList));
        sortedArrayListArrayList.add(this.sortMenusByNameAsc(menuArrayList));
        sortedArrayListArrayList.add(this.sortMenusByNameDesc(menuArrayList));


        // Ordered Menus Only Index Guide:
        // 9 Score Desc, 10-11 Cost Asc-Desc, 12-13 Frequency Asc-Desc
        // 14-15 Recency Asc-Desc, 16-17 Name Asc-Desc
        sortedArrayListArrayList.add(this.returnOnlyOrderedMenus(sortMenusByScore(menuArrayList)));
        sortedArrayListArrayList.add(this.returnOnlyOrderedMenus(sortMenusByCostAsc(menuArrayList)));
        sortedArrayListArrayList.add(this.returnOnlyOrderedMenus(sortMenusByCostDesc(menuArrayList)));
        sortedArrayListArrayList.add(this.returnOnlyOrderedMenus(sortMenusByFrequencyAsc(menuArrayList)));
        sortedArrayListArrayList.add(this.returnOnlyOrderedMenus(sortMenusByFrequencyDesc(menuArrayList)));
        sortedArrayListArrayList.add(this.returnOnlyOrderedMenus(sortMenusByRecencyAsc(menuArrayList)));
        sortedArrayListArrayList.add(this.returnOnlyOrderedMenus(sortMenusByRecencyDesc(menuArrayList)));
        sortedArrayListArrayList.add(this.returnOnlyOrderedMenus(sortMenusByNameAsc(menuArrayList)));
        sortedArrayListArrayList.add(this.returnOnlyOrderedMenus(sortMenusByNameDesc(menuArrayList)));

        // Cooked Menus Only Index Guide:
        // 18 Score Desc, 19-20 Cost Asc-Desc, 21-22 Frequency Asc-Desc
        // 23-24 Recency Asc-Desc, 25-26 Name Asc-Desc
        sortedArrayListArrayList.add(returnOnlyCookedMenus(sortMenusByScore(menuArrayList)));
        sortedArrayListArrayList.add(returnOnlyCookedMenus(sortMenusByCostAsc(menuArrayList)));
        sortedArrayListArrayList.add(returnOnlyCookedMenus(sortMenusByCostDesc(menuArrayList)));
        sortedArrayListArrayList.add(returnOnlyCookedMenus(sortMenusByFrequencyAsc(menuArrayList)));
        sortedArrayListArrayList.add(returnOnlyCookedMenus(sortMenusByFrequencyDesc(menuArrayList)));
        sortedArrayListArrayList.add(returnOnlyCookedMenus(sortMenusByRecencyAsc(menuArrayList)));
        sortedArrayListArrayList.add(returnOnlyCookedMenus(sortMenusByRecencyDesc(menuArrayList)));
        sortedArrayListArrayList.add(returnOnlyCookedMenus(sortMenusByNameAsc(menuArrayList)));
        sortedArrayListArrayList.add(returnOnlyCookedMenus(sortMenusByNameDesc(menuArrayList)));

    }

    // this method sorts an arraylist of Menus in score descending order.
    public ArrayList<Menu> sortMenusByScore(ArrayList<Menu> menuArrayList) {
        ArrayList<Menu> sortedMenuArrayList = new ArrayList<Menu>();

        // Score all menus.
        menuScorer mS = new menuScorer();
        for (int arraysize = 0; arraysize < menuArrayList.size(); arraysize++) {
            mS.menuScorer(menuArrayList.get(arraysize));
        }

        for (int arraysize = 0; arraysize < menuArrayList.size(); arraysize++) {
            sortedMenuArrayList.add(arraysize, null);
        }

        for (int i = 0; i < menuArrayList.size(); i++) {
            Menu compTarget = menuArrayList.get(i);
            int orderRank = 0;
            // Print Menus.Menu Names for Debugging
            //        System.out.println(compTarget.getName());
            for (int j = 0; j < menuArrayList.size(); j++) {

                int compareResult = compTarget.compareTo(menuArrayList.get(j));
//                System.out.println("Current Item is "+compTarget.getName());
//                System.out.println("Current Comp Item is "+menuArrayList.get(j).getName());
                if (menuArrayList.get(j).equals(compTarget) == false) {
                    switch (compareResult) {
                        case 1:
                            break;
                        case 0:
                            if (menuArrayList.get(j).getName().compareToIgnoreCase(compTarget.getName()) < 0) {
                                orderRank += 1;
                                break;
                            } else {
                                break;
                            }
                        case -1:
                            orderRank += 1;
                            break;

                    }
                }
//            System.out.println("Compare Result is:"+orderRank);
            }
//            System.out.println("OrderRank "+orderRank);
            sortedMenuArrayList.set(orderRank, compTarget);

            // println for debug
            //        System.out.println(orderRank+" "+sortedMenuArray[orderRank].getName());
        }
        return sortedMenuArrayList;
    }

    public ArrayList<Menu> sortMenusByCostAsc(ArrayList<Menu> menuArrayList) {
        // need to implement tiebreakers in the future
        ArrayList<Menu> sortedMenuArrayList = new ArrayList<Menu>();

        // Score all menus.
        menuScorer mS = new menuScorer();
        for (int arraysize = 0; arraysize < menuArrayList.size(); arraysize++) {
            mS.menuScorer(menuArrayList.get(arraysize));
        }

        for (int arraysize = 0; arraysize < menuArrayList.size(); arraysize++) {
            sortedMenuArrayList.add(arraysize, null);
        }

        for (int i = 0; i < menuArrayList.size(); i++) {
            Menu compTarget = menuArrayList.get(i);
            int orderRank = 0;
            // Print Menus.Menu Names for Debugging
            //        System.out.println(compTarget.getName());
            for (int j = 0; j < menuArrayList.size(); j++) {
                double targetCost = compTarget.getCost();
                double compareCost = menuArrayList.get(j).getCost();
//                System.out.println("Current Item is "+compTarget.getName());
//                System.out.println("Current Comp Item is "+menuArrayList.get(j).getName());
                if (targetCost < compareCost) {
                }
                if (targetCost > compareCost) {
                    orderRank += 1;
                }
                if (targetCost == compareCost) {
                    if (menuArrayList.get(j).getName().compareToIgnoreCase(compTarget.getName()) < 0) {
                        orderRank += 1;
                    }
                }
//                System.out.print(compTarget.getName() + " " + menuArrayList.get(j).getName());
//                System.out.print(" " + compTarget.getCost() + " " + menuArrayList.get(j).getCost());
//                System.out.println(orderRank);
            }
//            System.out.println("Compare Result is:"+orderRank);
//            System.out.println("OrderRank "+orderRank);
//            System.out.println(compTarget.getName() + " " + orderRank);
            sortedMenuArrayList.set(orderRank, compTarget);

            // println for debug
            //        System.out.println(orderRank+" "+sortedMenuArray[orderRank].getName());
        }
        return sortedMenuArrayList;
    }

    public ArrayList<Menu> sortMenusByCostDesc(ArrayList<Menu> menuArrayList) {
        // need to implement tiebreakers in the future
        ArrayList<Menu> sortedMenuArrayList = new ArrayList<Menu>();

        // Score all menus.
        menuScorer mS = new menuScorer();
        for (int arraysize = 0; arraysize < menuArrayList.size(); arraysize++) {
            mS.menuScorer(menuArrayList.get(arraysize));
        }

        for (int arraysize = 0; arraysize < menuArrayList.size(); arraysize++) {
            sortedMenuArrayList.add(arraysize, null);
        }

        for (int i = 0; i < menuArrayList.size(); i++) {
            Menu compTarget = menuArrayList.get(i);
            int orderRank = 0;
            // Print Menus.Menu Names for Debugging
            //        System.out.println(compTarget.getName());
            for (int j = 0; j < menuArrayList.size(); j++) {
                double targetCost = compTarget.getCost();
                double compareCost = menuArrayList.get(j).getCost();
//                System.out.println("Current Item is "+compTarget.getName());
//                System.out.println("Current Comp Item is "+menuArrayList.get(j).getName());
                if (targetCost < compareCost) {
                    orderRank += 1;
                }
                if (targetCost > compareCost) {
                }
                if (targetCost == compareCost) {
                    if (menuArrayList.get(j).getName().compareToIgnoreCase(compTarget.getName()) < 0) {
                        orderRank += 1;
                    }
                }
//                System.out.print(compTarget.getName() + " " + menuArrayList.get(j).getName());
//                System.out.print(" " + compTarget.getCost() + " " + menuArrayList.get(j).getCost());
//                System.out.println(orderRank);
            }
//            System.out.println("Compare Result is:"+orderRank);
//            System.out.println("OrderRank "+orderRank);
//            System.out.println(compTarget.getName() + " " + orderRank);
            sortedMenuArrayList.set(orderRank, compTarget);

            // println for debug
            //        System.out.println(orderRank+" "+sortedMenuArray[orderRank].getName());
        }
        return sortedMenuArrayList;
    }

    public ArrayList<Menu> sortMenusByNameAsc(ArrayList<Menu> menuArrayList) {
        // need to implement tiebreakers in the future
        ArrayList<Menu> sortedMenuArrayList = new ArrayList<Menu>();

        // Score all menus.
        menuScorer mS = new menuScorer();
        for (int arraysize = 0; arraysize < menuArrayList.size(); arraysize++) {
            mS.menuScorer(menuArrayList.get(arraysize));
        }

        for (int arraysize = 0; arraysize < menuArrayList.size(); arraysize++) {
            sortedMenuArrayList.add(arraysize, null);
        }
        for (int i = 0; i < menuArrayList.size(); i++) {
            Menu compTarget = menuArrayList.get(i);
            int orderRank = 0;
            // Print Menus.Menu Names for Debugging
            //        System.out.println(compTarget.getName());
            for (int j = 0; j < menuArrayList.size(); j++) {
                String targetName = compTarget.getName();
                String compareCost = menuArrayList.get(j).getName();
                if (menuArrayList.get(j).getName().compareToIgnoreCase(compTarget.getName()) < 0) {
                    orderRank += 1;
                }
            }
            sortedMenuArrayList.set(orderRank, compTarget);
//            System.out.println(compTarget.getName() + " " + orderRank);
        }
        return sortedMenuArrayList;
    }

    public ArrayList<Menu> sortMenusByNameDesc(ArrayList<Menu> menuArrayList) {
        // need to implement tiebreakers in the future
        ArrayList<Menu> sortedMenuArrayList = new ArrayList<Menu>();

        // Score all menus.
        menuScorer mS = new menuScorer();
        for (int arraysize = 0; arraysize < menuArrayList.size(); arraysize++) {
            mS.menuScorer(menuArrayList.get(arraysize));
        }

        for (int arraysize = 0; arraysize < menuArrayList.size(); arraysize++) {
            sortedMenuArrayList.add(arraysize, null);
        }
        for (int i = 0; i < menuArrayList.size(); i++) {
            Menu compTarget = menuArrayList.get(i);
            int orderRank = 0;
            // Print Menus.Menu Names for Debugging
            //        System.out.println(compTarget.getName());
            for (int j = 0; j < menuArrayList.size(); j++) {
                String targetName = compTarget.getName();
                String compareCost = menuArrayList.get(j).getName();
                if (menuArrayList.get(j).getName().compareToIgnoreCase(compTarget.getName()) > 0) {
                    orderRank += 1;
                }
            }
            sortedMenuArrayList.set(orderRank, compTarget);
        }
        return sortedMenuArrayList;
    }

    public ArrayList<Menu> sortMenusByFrequencyAsc(ArrayList<Menu> menuArrayList) {
        // need to implement tiebreakers in the future
        ArrayList<Menu> sortedMenuArrayList = new ArrayList<Menu>();

        // Score all menus.
        menuScorer mS = new menuScorer();
        for (int arraysize = 0; arraysize < menuArrayList.size(); arraysize++) {
            mS.menuScorer(menuArrayList.get(arraysize));
        }

        for (int arraysize = 0; arraysize < menuArrayList.size(); arraysize++) {
            sortedMenuArrayList.add(arraysize, null);
        }

        for (int i = 0; i < menuArrayList.size(); i++) {
            Menu compTarget = menuArrayList.get(i);
            int orderRank = 0;
            // Print Menus.Menu Names for Debugging
            //        System.out.println(compTarget.getName());
            for (int j = 0; j < menuArrayList.size(); j++) {
                double targetCost = compTarget.getFrequency();
                double compareCost = menuArrayList.get(j).getFrequency();
//                System.out.println("Current Item is "+compTarget.getName());
//                System.out.println("Current Comp Item is "+menuArrayList.get(j).getName());
                if (targetCost < compareCost) {
                }
                if (targetCost > compareCost) {
                    orderRank += 1;
                }
                if (targetCost == compareCost) {
                    if (menuArrayList.get(j).getName().compareToIgnoreCase(compTarget.getName()) < 0) {
                        orderRank += 1;
                    }
                }
//                System.out.print(compTarget.getName() + " " + menuArrayList.get(j).getName());
//                System.out.print(" " + compTarget.getCost() + " " + menuArrayList.get(j).getCost());
//                System.out.println(orderRank);
            }
//            System.out.println("Compare Result is:"+orderRank);
//            System.out.println("OrderRank "+orderRank);
//            System.out.println(compTarget.getName() + " " + orderRank);
            sortedMenuArrayList.set(orderRank, compTarget);

            // println for debug
            //        System.out.println(orderRank+" "+sortedMenuArray[orderRank].getName());
        }
        return sortedMenuArrayList;
    }

    public ArrayList<Menu> sortMenusByFrequencyDesc(ArrayList<Menu> menuArrayList) {
        // need to implement tiebreakers in the future
        ArrayList<Menu> sortedMenuArrayList = new ArrayList<Menu>();

        // Score all menus.
        menuScorer mS = new menuScorer();
        for (int arraysize = 0; arraysize < menuArrayList.size(); arraysize++) {
            mS.menuScorer(menuArrayList.get(arraysize));
        }

        for (int arraysize = 0; arraysize < menuArrayList.size(); arraysize++) {
            sortedMenuArrayList.add(arraysize, null);
        }

        for (int i = 0; i < menuArrayList.size(); i++) {
            Menu compTarget = menuArrayList.get(i);
            int orderRank = 0;
            // Print Menus.Menu Names for Debugging
            //        System.out.println(compTarget.getName());
            for (int j = 0; j < menuArrayList.size(); j++) {
                double targetCost = compTarget.getFrequency();
                double compareCost = menuArrayList.get(j).getFrequency();
//                System.out.println("Current Item is "+compTarget.getName());
//                System.out.println("Current Comp Item is "+menuArrayList.get(j).getName());
                if (targetCost < compareCost) {
                    orderRank += 1;
                }
                if (targetCost > compareCost) {
                }
                if (targetCost == compareCost) {
                    if (menuArrayList.get(j).getName().compareToIgnoreCase(compTarget.getName()) < 0) {
                        orderRank += 1;
                    }
                }
//                System.out.print(compTarget.getName() + " " + menuArrayList.get(j).getName());
//                System.out.print(" " + compTarget.getCost() + " " + menuArrayList.get(j).getCost());
//                System.out.println(orderRank);
            }
//            System.out.println("Compare Result is:"+orderRank);
//            System.out.println("OrderRank "+orderRank);
//            System.out.println(compTarget.getName() + " " + orderRank);
            sortedMenuArrayList.set(orderRank, compTarget);

            // println for debug
            //        System.out.println(orderRank+" "+sortedMenuArray[orderRank].getName());
        }
        return sortedMenuArrayList;
    }

    public ArrayList<Menu> sortMenusByRecencyAsc(ArrayList<Menu> menuArrayList) {
        // need to implement tiebreakers in the future
        ArrayList<Menu> sortedMenuArrayList = new ArrayList<Menu>();

        // Score all menus.
        menuScorer mS = new menuScorer();
        for (int arraysize = 0; arraysize < menuArrayList.size(); arraysize++) {
            mS.menuScorer(menuArrayList.get(arraysize));
        }

        for (int arraysize = 0; arraysize < menuArrayList.size(); arraysize++) {
            sortedMenuArrayList.add(arraysize, null);
        }

        for (int i = 0; i < menuArrayList.size(); i++) {
            Menu compTarget = menuArrayList.get(i);
            int orderRank = 0;
            // Print Menus.Menu Names for Debugging
            //        System.out.println(compTarget.getName());
            for (int j = 0; j < menuArrayList.size(); j++) {
                double targetCost = compTarget.getRecency();
                double compareCost = menuArrayList.get(j).getRecency();
//                System.out.println("Current Item is "+compTarget.getName());
//                System.out.println("Current Comp Item is "+menuArrayList.get(j).getName());
                if (targetCost < compareCost) {
                }
                if (targetCost > compareCost) {
                    orderRank += 1;
                }
                if (targetCost == compareCost) {
                    if (menuArrayList.get(j).getName().compareToIgnoreCase(compTarget.getName()) < 0) {
                        orderRank += 1;
                    }
                }
//                System.out.print(compTarget.getName() + " " + menuArrayList.get(j).getName());
//                System.out.print(" " + compTarget.getCost() + " " + menuArrayList.get(j).getCost());
//                System.out.println(orderRank);
            }
//            System.out.println("Compare Result is:"+orderRank);
//            System.out.println("OrderRank "+orderRank);
//            System.out.println(compTarget.getName() + " " + orderRank);
            sortedMenuArrayList.set(orderRank, compTarget);

            // println for debug
            //        System.out.println(orderRank+" "+sortedMenuArray[orderRank].getName());
        }
        return sortedMenuArrayList;
    }

    public ArrayList<Menu> sortMenusByRecencyDesc(ArrayList<Menu> menuArrayList) {
        // need to implement tiebreakers in the future
        ArrayList<Menu> sortedMenuArrayList = new ArrayList<Menu>();

        // Score all menus.
        menuScorer mS = new menuScorer();
        for (int arraysize = 0; arraysize < menuArrayList.size(); arraysize++) {
            mS.menuScorer(menuArrayList.get(arraysize));
        }

        for (int arraysize = 0; arraysize < menuArrayList.size(); arraysize++) {
            sortedMenuArrayList.add(arraysize, null);
        }

        for (int i = 0; i < menuArrayList.size(); i++) {
            Menu compTarget = menuArrayList.get(i);
            int orderRank = 0;
            // Print Menus.Menu Names for Debugging
            //        System.out.println(compTarget.getName());
            for (int j = 0; j < menuArrayList.size(); j++) {
                double targetCost = compTarget.getRecency();
                double compareCost = menuArrayList.get(j).getRecency();
//                System.out.println("Current Item is "+compTarget.getName());
//                System.out.println("Current Comp Item is "+menuArrayList.get(j).getName());
                if (targetCost < compareCost) {
                    orderRank += 1;
                }
                if (targetCost > compareCost) {
                }
                if (targetCost == compareCost) {
                    if (menuArrayList.get(j).getName().compareToIgnoreCase(compTarget.getName()) < 0) {
                        orderRank += 1;
                    }
                }
//                System.out.print(compTarget.getName() + " " + menuArrayList.get(j).getName());
//                System.out.print(" " + compTarget.getCost() + " " + menuArrayList.get(j).getCost());
//                System.out.println(orderRank);
            }
//            System.out.println("Compare Result is:"+orderRank);
//            System.out.println("OrderRank "+orderRank);
//            System.out.println(compTarget.getName() + " " + orderRank);
            sortedMenuArrayList.set(orderRank, compTarget);

            // println for debug
            //        System.out.println(orderRank+" "+sortedMenuArray[orderRank].getName());
        }
        return sortedMenuArrayList;
    }

    public ArrayList<ArrayList<Menu>> returnArrayLists()
    {
        return sortedArrayListArrayList;
    }

    public ArrayList<Menu> returnOnlyCookedMenus(ArrayList<Menu> menuArrayList) {
        ArrayList<Menu> cookedArrayList = new ArrayList<Menu>();
        menuArrayList.forEach((menu)-> {
            if(menu.getType().equals("Cooked")) {
                cookedArrayList.add(menu);}
        });
        return cookedArrayList;
    }

    public ArrayList<Menu> returnOnlyOrderedMenus(ArrayList<Menu> menuArrayList) {
        ArrayList<Menu> orderedArrayList = new ArrayList<Menu>();
        menuArrayList.forEach((menu)-> {
            if(menu.getType().equals("Ordered")) {
                orderedArrayList.add(menu);}
        });
        return orderedArrayList;
    }

}
