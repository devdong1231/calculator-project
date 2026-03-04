import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Calculator {

    public static void calculator(Scanner scanner, List<Double> history){
        double firstNum;
        double secondNum;
        String op;
        char choice = ' ';
        double result = 0.0;

        while(true){
            System.out.println("=== Java 계산기 ===");

            if(!history.isEmpty()){ // 이전 결과 사용 여부
                System.out.print("이전 결과("+String.format("%.1f", history.getLast())+")를 사용하시겠습니까? (y/n):");
                choice = scanner.next().charAt(0);
                if(choice != 'y' && choice != 'Y' && choice != 'n' && choice != 'N'){
                    System.out.println("y 또는 n만 입력해주세요.");
                    scanner.nextLine();
                    continue;
                }
            }

            // 첫 번째 수 입력
            if(choice != 'y' && choice != 'Y'){ // 이전 결과가 존재하지 않거나 / n을 선택한 경우
                System.out.print("첫 번째 숫자를 입력하세요: ");
                // 숫자가 아닌 경우 입력 처리
                try{
                    firstNum = scanner.nextInt();
                } catch (InputMismatchException e){
                    System.out.println("숫자를 입력해주세요.");
                    scanner.nextLine(); // 버퍼 비우기
                    continue;
                }
            }
            else{ // 이전 결과를 사용하는 경우
                firstNum = history.getLast();
            }



            // 연산자 입력
            System.out.print("연산자를 입력하세요 (+, -, *, /, %, ^, sqrt): ");
            // 잘못된 연산자 입력 처리
            try{
                op = scanner.next();
                if(!(op.equals("+") || op.equals("-") || op.equals("*") || op.equals("/") || op.equals("%") || op.equals("^") || op.equals("sqrt")))
                    throw new IllegalArgumentException("지원하지 않는 연산자입니다.");
            } catch(IllegalArgumentException e){
                System.out.println(e.getMessage());
                scanner.nextLine(); // 버퍼 비우기
                continue;
            }
            if(op.equals("sqrt")){
                result = Math.sqrt(firstNum);
                System.out.println("결과: " + String.format("%.1f", firstNum) + "의 제곱근은 " + result + "입니다.");
            }
            else{
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
                if(op.equals("/") && secondNum == 0){
                    System.out.println("0으로 나눌 수 없습니다.");
                    continue;
                }

                //결과 출력
                result = switch (op) {
                    case "+" -> firstNum + secondNum;
                    case "-" -> firstNum - secondNum;
                    case "*" -> firstNum * secondNum;
                    case "/" -> firstNum / secondNum;
                    case "%" -> firstNum % secondNum;
                    case "^" -> Math.pow(firstNum, secondNum);
                    default -> result;
                };
                System.out.println("결과: " + String.format("%.1f", firstNum) + " " + op + " " + String.format("%.1f", secondNum) + " = " + result + "\n");
            }
            history.add(result);

            // 계속/종료 선택 기능
            while(true){
                System.out.print("계속 계산하시겠습니까? (y/n): ");
                char repeat = scanner.next().charAt(0);
                // n / N의 경우
                if(repeat == 'n' || repeat == 'N'){
                    System.out.println("계산기를 종료합니다.");
                    return;
                }
                // y / Y의 경우
                else if(repeat == 'y' || repeat == 'Y')
                    break;
                    // 입력 오류
                else{
                    System.out.println("y 또는 n만 입력해주세요.");
                    scanner.nextLine();
                }
            }
        }
    }

    public static void showHistory(List<Double> history){
        for(int i = 0; i < history.size(); i++){
            System.out.println((i+1) + "번째 계산 결과: " + history.get(i));
        }
    }

    public static void deleteHistory(List<Double> history){
        history.clear();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Double> history = new ArrayList<>();

        while(true){
            System.out.println("=== 계산기 메뉴 ===");
            System.out.println("1. 계산하기\n2. 계산 이력 보기\n3. 이력 지우기\n0. 종료");
            System.out.print("선택: ");
            int choice = scanner.nextInt();
            switch (choice){
                case 0: scanner.close(); return; // 종료
                case 1: calculator(scanner, history); break; // 계산하기
                case 2: showHistory(history); break; // 계산 이력 보기
                case 3: deleteHistory(history); break; // 이력 지우기
                default: // 잘못된 입력 처리
                    System.out.println("잘못된 입력입니다. 다시 입력해주세요.");
            }
        }
    }

}