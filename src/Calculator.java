import java.util.InputMismatchException;
import java.util.Scanner;

public class Calculator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double firstNum;
        double secondNum;
        char op;
        double result = 0.0;

        while(true){
            System.out.println("=== Java 계산기 ===");

            // 첫 번째 수 입력
            System.out.print("첫 번째 숫자를 입력하세요: ");
            // 숫자가 아닌 경우 입력 처리
            try{
                firstNum = scanner.nextInt();
            } catch (InputMismatchException e){
                System.out.println("숫자를 입력해주세요.");
                scanner.nextLine(); // 버퍼 비우기
                continue;
            }


            // 연산자 입력
            System.out.print("연산자를 입력하세요 (+, -, *, /): ");
            // 잘못된 연산자 입력 처리
            try{
                op = scanner.next().charAt(0);
                if(op != '+' && op != '-' && op != '*' && op != '/')
                    throw new IllegalArgumentException("지원하지 않는 연산자입니다.");
            } catch(IllegalArgumentException e){
                System.out.println(e.getMessage());
                scanner.nextLine(); // 버퍼 비우기
                continue;
            }

            // 두 번째 수 입력
            System.out.print("두 번째 숫자를 입력하세요: ");
            // 숫자가 아닌 경우 입력 처리
            try{
                secondNum = scanner.nextInt();
            } catch (InputMismatchException e){
                System.out.println("숫자를 입력해주세요.");
                scanner.nextLine();
                continue;
            }
            // divisionByZero 처리
            if(op == '/' && secondNum == 0){
                System.out.println("0으로 나눌 수 없습니다.");
                continue;
            }

            //결과 출력
            result = switch (op) {
                case '+' -> firstNum + secondNum;
                case '-' -> firstNum - secondNum;
                case '*' -> firstNum * secondNum;
                case '/' -> firstNum / secondNum;
                default -> result;
            };
            System.out.println("결과: " + String.format("%.1f", firstNum) + " " + op + " " + String.format("%.1f", secondNum) + " = " + result + "\n");

            // 계속/종료 선택 기능
            while(true){
                System.out.print("계속 계산하시겠습니까? (y/n): ");
                char repeat = scanner.next().charAt(0);
                // n / N의 경우
                if(repeat == 'n' || repeat == 'N'){
                    System.out.println("계산기를 종료합니다.");
                    scanner.close();
                    return;
                }
                // y / Y의 경우
                else if(repeat == 'y' || repeat == 'Y')
                    break;
                // 입력 오류
                else
                    System.out.println("y 또는 n만 입력해주세요.");

            }

        }



    }

}