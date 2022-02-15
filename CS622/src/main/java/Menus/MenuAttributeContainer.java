package Menus;

public class MenuAttributeContainer<T> {
    private T attributeValue;


    public MenuAttributeContainer(T attributeValueInput){
        this.attributeValue = attributeValueInput;
    }

    public T getAttribute() {
        return attributeValue;
    }
    public void setAttribute(T attributeValueInput) {
        this.attributeValue = attributeValueInput;
    }


}
