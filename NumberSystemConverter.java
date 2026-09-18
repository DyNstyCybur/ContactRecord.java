package converter;

import java.util.Scanner;
import java.util.Map;


public class NumberSystemConverter {
    
    public static void main(String[] args) {
        System.out.println("=== NUMBER SYSTEM CONVERTER v1.0 ===");
        System.out.println("Package: converter | Eclipse Ready\n");
        
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        
        while (running) {
            try {
                System.out.println("-".repeat(55));
                
                System.out.print("Enter number: ");
                String inputValue = scanner.nextLine().trim();
                
                System.out.print("Source base (2/B/8/O/10/D/16/H): ");
                String sourceBaseInput = scanner.nextLine().trim();
                
                System.out.print("Target base (2/B/8/O/10/D/16/H): ");
                String targetBaseInput = scanner.nextLine().trim();
                
                int sourceBase = parseBase(sourceBaseInput);
                int targetBase = parseBase(targetBaseInput);
                
                if (sourceBase <= 1 || targetBase <= 1) {
                    System.out.println("X ERROR: Invalid base! Use: 2(B),8(O),10(D),16(H)");
                    continue;
                }
                
                String validationMsg = validateInput(inputValue, sourceBase);
                if (!validationMsg.equals("VALID")) {
                    System.out.println("X ERROR: " + validationMsg);
                    continue;
                }
                
                long decimal = stringToDecimal(inputValue, sourceBase);
                String output = decimalToString(decimal, targetBase);
                
                showResults(inputValue, sourceBase, decimal, output, targetBase);
                
                System.out.print("\n Another conversion? (y/n): ");
                running = scanner.nextLine().trim().toLowerCase().startsWith("y");
                
            } catch (Exception e) {
                System.out.println("X System error: " + e.getMessage());
            }
        }
        
        System.out.println("\n Program ended. Thank you!");
        scanner.close();
    }
    
    private static int parseBase(String baseStr) {
        Map<String, Integer> baseMap = Map.ofEntries(
            Map.entry("2", 2), Map.entry("B", 2), Map.entry("BINARY", 2),
            Map.entry("8", 8), Map.entry("O", 8), Map.entry("OCTAL", 8),
            Map.entry("10", 10), Map.entry("D", 10), Map.entry("DECIMAL", 10),
            Map.entry("16", 16), Map.entry("H", 16), Map.entry("HEX", 16)
        );
        return baseMap.getOrDefault(baseStr.toUpperCase(), 0);
    }
    
    private static String validateInput(String value, int base) {
        if (value == null || value.trim().isEmpty()) {
            return "Empty input";
        }
        if (value.equals("0")) {
            return "VALID";
        }
        
        String allowed = switch (base) {
            case 2 -> "01";
            case 8 -> "01234567";
            case 10 -> "0123456789";
            case 16 -> "0123456789ABCDEF";
            default -> "";
        };
        
        for (char c : value.toUpperCase().toCharArray()) {
            if (allowed.indexOf(c) < 0) {
                return String.format("Invalid char '%c' for base %d", c, base);
            }
        }
        return "VALID";
    }
    
    private static long stringToDecimal(String value, int base) {
        if (value.equals("0")) return 0;
        
        value = value.toUpperCase();
        long result = 0;
        
        for (int i = value.length() - 1; i >= 0; i--) {
            char c = value.charAt(i);
            int digit = getDigitValue(c);
            result = result * base + digit;
        }
        return result;
    }
    
    private static int getDigitValue(char c) {
        if (Character.isDigit(c)) {
            return c - '0';
        }
        return 10 + (c - 'A');
    }
    
    private static String decimalToString(long decimal, int base) {
        if (decimal == 0) return "0";
        
        StringBuilder sb = new StringBuilder();
        while (decimal > 0) {
            int rem = (int)(decimal % base);
            sb.append(rem < 10 ? 
                (char)('0' + rem) : 
                (char)('A' + rem - 10));
            decimal /= base;
        }
        return sb.reverse().toString();
    }
    
    private static void showResults(String input, int fromBase, long decimal, 
                                  String output, int toBase) {
        Map<Integer, String> names = Map.of(
            2, "BINARY", 8, "OCTAL", 10, "DECIMAL", 16, "HEXADECIMAL"
        );
        
        System.out.println("\n" + "=".repeat(60));
        System.out.println("CONVERSION COMPLETE!");
        System.out.printf("%-25s → %s%n", 
            input + " [" + names.getOrDefault(fromBase, "?") + "]", 
            output + " [" + names.getOrDefault(toBase, "?") + "]");
        System.out.printf("Decimal equivalent: %d%n", decimal);
        System.out.println("=".repeat(60));
    }
}