import java.util.Scanner;

/**
 * @author hamza.ahmed 05/09/19
 * Evaluate regular expression
 */
public class Operation implements MathExpressions {
    //private fields
    //array containing all the operators and numbers
    private String[] expArray;
    private int result=0;
    private boolean calculated=false;
    private boolean divide=false;
    private boolean multiply=false;
    private boolean curly=false;
    private boolean round=false;
    private boolean square=false;

    //public members
    public void setExpArray() {
        Scanner input=new Scanner(System.in);
        //input expression
        String exp=input.nextLine();
        //converting expresson to char array
        char[] arr=exp.toCharArray();
        expArray=new String[arr.length];
        //converting char array to string array
        for(int i=0;i<arr.length;i++){
            expArray[i]=Character.toString(arr[i]);
        }
    }

    public void setExpArray(String exp) {
        char[] arr=exp.toCharArray();
        expArray=new String[arr.length];
        //converting char array to string array
        for(int i=0;i<arr.length;i++){
            expArray[i]=Character.toString(arr[i]);
        }
    }



    @Override
    public void exploreExpression() {
        for(int i=0;i<expArray.length;i++){
            //if dollar sign in the array skip it
            if(expArray[i].equals("$")){
                continue;
            }
            checkBrackets();
            String chunk="";
            //if we get opening bracket {
            if(expArray[i].equals("{")&& !round){
                int j=i+1;
                //get chunk of array till closing bracket and replace $ sign in original array so that we can trim array based on $ sign}
                while (!expArray[j].equals("}")){
                    chunk+=expArray[j];
                    expArray[j]="$";
                    j++;
                }
                expArray[j] = "$";
                //evaluate exprerssion will compute the chunk obtained
                expArray[i]=evaluateExpression(chunk)+"";
                //trim function will delete the $ from the array
                trim();
                i=-1;
                curly=false;
            }else if(expArray[i].equals("(")){
                        int j=i+1;
                        //get chunk of array till closing bracket and replace $ sign in original array so that we can trim array based on $ sign}
                        while (!expArray[j].equals(")")){
                            chunk+=expArray[j];
                            expArray[j]="$";
                            j++;
                        }
                        expArray[j] = "$";
                        //evaluate exprerssion will compute the chunk obtained
                        expArray[i]=evaluateExpression(chunk)+"";
                        //trim function will delete the $ from the array
                        trim();
                        i=-1;
                        round=false;
                        continue;
                //if we get opening bracket [
            }else
                if(expArray[i].equals("[") && ! round && !curly){
                    int j=i+1;
                    //get chunk of array till closing bracket and replace $ sign in original array so that we can trim array based on $ sign}
                    while (!expArray[j].equals("]")){
                        chunk+=expArray[j];
                        expArray[j]="$";
                        j++;
                }

                    expArray[j] = "$";
                    //evaluate exprerssion will compute the chunk obtained
                    expArray[i]=evaluateExpression(chunk)+"";
                    //trim function will delete the $ from the array
                    trim();
                    i=-1;
                    square=false;
                    continue;

                //if no brackets are found
            }else if(!curly && !round && !square) {
                        i=0;
                        while (i<expArray.length) {
                            if(!expArray[i].equals("$")) {
                                chunk += expArray[i];
                            }
                            i++;
                        }
                        //printing the final result
                        System.out.println("\nFinal result: "+evaluateExpression(chunk));
            }

        }
    }

    //function to evaluate the chunk obtained from inside the brackets
    @Override
    public int evaluateExpression(String exp) {
        if(exp.length()>1){
            calculated=false;
        }
        //converting string to char array
        char[] expArr=exp.toCharArray();
        String[] arrExp=new String[expArr.length];

        //converting char array to string array
        for(int i=0;i<expArr.length;i++){
            arrExp[i]=Character.toString(expArr[i]);
        }
        //check if there is divide operator in the expression
        for(int i=0;i<arrExp.length;i++){
            if(arrExp[i].equals("/")){
                divide=true;
            }
            //check if there is * operator in the expression
            if(arrExp[i].equals("*")){
                multiply=true;
            }

        }
        //if $ sign found skip the iteration
        for (int i=1;i<arrExp.length;i++){
            if(arrExp[i].equals("$")){
                continue;
            }
            //if the current value on index is / operator
            if(arrExp[i].equals("/")){
                if(calculated){
                    break;
                }
                //take previous and next values of the operator and parse them to int
                int num1=Integer.parseInt(arrExp[i-1]);
                int num2=Integer.parseInt(arrExp[i+1]);
                result=num1/num2;
                arrExp[i]="$";
                arrExp[i+1]="$";
                arrExp[i-1]=result+"";
                trim(arrExp);
//                arr=trimArray(arr).toCharArray();
//                calChunk(arr.toString());
            }
            //if the current value on index is * operator
            else if(arrExp[i].equals("*")){
                if(!divide) {
                    if (calculated) {
                        break;
                    }
                    //take previous and next values of the operator and parse them to int
                    int num1 = Integer.parseInt(arrExp[i - 1]);
                    int num2 = Integer.parseInt(arrExp[i + 1]);
                    result = num1 * num2;
                    arrExp[i]="$";
                    arrExp[i+1]="$";
                    arrExp[i-1]=result+"";
                    trim(arrExp);
                }
//                arr=trimArray(arr).toCharArray();
//                calChunk(arr.toString());
            }
            //if the current value on index is + operator
            else if(arrExp[i].equals("+")){
                if(!divide && !multiply) {
                    if (calculated) {
                        break;
                    }
                    //take previous and next values of the operator and parse them to int
                    int num1=Integer.parseInt(arrExp[i-1]);
                    int num2=Integer.parseInt(arrExp[i+1]);
                    result=num1+num2;
//                    arr[i]=Integer.toString(res);
                    arrExp[i]="$";
                    arrExp[i+1]="$";
                    arrExp[i-1]=result+"";

                    trim(arrExp);
                }

//                calChunk(arr.toString());
                //if the current value on index is - operator
            }else if(arrExp[i].equals("-")){
                if(calculated){
                    break;
                }
                if(!divide && !multiply) {
                    //take previous and next values of the operator and parse them to int
                    int num1=Integer.parseInt(arrExp[i-1]);
                    int num2=Integer.parseInt(arrExp[i+1]);
                    result=num1-num2;
                    arrExp[i]="$";
                    arrExp[i+1]="$";
                    arrExp[i-1]=result+"";

                    trim(arrExp);
                }
//                arr=trimArray(arr).toCharArray();
//                calChunk(arr.toString());
            }

        }
        return result;
    }

    @Override
    public void trim() {
        String trimmed= "";
        int size=0;
        String[] tempArr=expArray;
        //initialising again the original array after removing the $ sign
        for (int i=0;i<expArray.length;i++){
            if(expArray[i]!="$"){
                size++;
                trimmed+=expArray[i];
            }
        }
        expArray=new String[size];
        int k=0;
        for (int i=0;i<tempArr.length;i++){
            if(tempArr[i]!="$"){
                expArray[k++]=tempArr[i];
            }
        }
        divide=false;
        multiply=false;
    }

    @Override
    public void trim(String[] exp) {
        String trimmed= "";
        //removing $ sign from the provided
        for (int i=0;i<exp.length;i++){
            if(exp[i]!="$"){
                trimmed+=exp[i];
            }
        }
        if(trimmed.length()<=2){
            calculated=true;
            result=Integer.parseInt(trimmed);
        }
        divide=false;
        multiply=false;
        evaluateExpression(trimmed);
    }

    @Override
    public void checkBrackets() {
        for(int h=0;h<expArray.length;h++){
            if(expArray[h].equals("{")){
                curly=true;
            }else if(expArray[h].equals("[")){
                square=true;
            }else if(expArray[h].equals("(")){
                round=true;
            }
        }
    }

}
