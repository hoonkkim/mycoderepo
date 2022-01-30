package Menus;

public class OrderedMenu extends Menu{
        // Defaults for attribute values are -1 for debugging.
        // Menus.OrderedMenu Specific Attributes
        private double deliveryCost = -1;
        private String deliveryServiceName = "DefaultServiceName";
        private double foodCost = -1;
        // Constructors: 1 empty, 1 full
        public OrderedMenu() {}
        public OrderedMenu(String nameInput, double costInput, int frequencyInput
                , int recencyInput, String deliveryServiceInput) {
            super.setName(nameInput);
            super.setCost(costInput);
            super.setFrequency(frequencyInput);
            super.setRecency(recencyInput);
            deliveryServiceName = deliveryServiceInput;
        }

        // Overriding getcost in case cost is empty. This really shouldn't happen aside from edge cases.
        @Override
        public double getCost() {
            if (super.getCost() != -1) {
                return super.getCost();
            }
            if (super.getCost() == -1 & deliveryCost != -1 & foodCost != -1) {
                return (deliveryCost + foodCost);
            }
            if (super.getCost() == -1 & deliveryCost == -1 & foodCost != -1) {
                return (foodCost);
            }
            else {
                return -1;
            }
        }

        // Setter for Cost when only the component costs are known.
        public void setCost(double foodcost, double deliverycost) {
            super.setCost(foodcost+deliverycost);
        }

        // OrderedSpecific Methods
        // Getters and setters for delivery service, delivery cost, & food cost
        public String getDeliveryServiceName() {
            return deliveryServiceName;
        }

        public double getDeliveryCost() { return deliveryCost; }

        public double getFoodCost() { return foodCost; }

        public void setDeliveryService(String deliversServiceEntry) {
            deliveryServiceName = deliversServiceEntry;
        }
        public void setDeliveryCost(double deliveryCostInput) {deliveryCost = deliveryCostInput;}
        public void setFoodCost(double foodCostInput) {foodCost = foodCostInput;}

}