/**
 * @author hamza.ahmed 05/09/19
 * Evaluate regular expression
 */
public class Test {
    public static void main(String[] args) {
        Operation operation=new Operation();
        operation.setExpArray();
        operation.exploreExpression();

    }
    public static void Test1(){
        Operation operation=new Operation();
        operation.setExpArray("{2+(7-5)*4/2}");
        operation.exploreExpression();
    }
    public static void Test2(){
        Operation operation=new Operation();
        operation.setExpArray("[9+{4+2*(6/3)-3}]");
        operation.exploreExpression();
    }
}
