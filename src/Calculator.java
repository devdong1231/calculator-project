import java.util.Scanner;

public class Calculator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Java 계산기 ===");
        
        // 첫 번째 수 입력
        System.out.print("첫 번째 숫자를 입력하세요: ");
        double firstNum = scanner.nextInt();
        
        // 연산자 입력
        System.out.print("연산자를 입력하세요 (+, -, *, /): ");
        char op = scanner.next().charAt(0);

        // 두 번째 수 입력
        System.out.print("두 번째 숫자를 입력하세요: ");
        double secondNum = scanner.nextInt();

        double result = 0.0;
        //결과 출력
        switch (op){
            case '+': result = firstNum + secondNum; break;
            case '-': result = firstNum - secondNum; break;
            case '*': result = firstNum * secondNum; break;
            case '/': result = firstNum / secondNum; break;
        }
        System.out.println("결과: " + String.format("%.1f", firstNum) + " " + op + " " + String.format("%.1f", secondNum) + " = " + result);

        System.out.println("계산기를 종료합니다.");
        scanner.close();
    }

}