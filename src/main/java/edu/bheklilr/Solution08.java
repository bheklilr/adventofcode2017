package edu.bheklilr;

import java.util.*;
import java.util.stream.Collectors;

public class Solution08 extends Solution<Integer> {

    private static List<Instruction> getInstructions() {
        return getInputLines("08ms")
                .map(Instruction::parse)
                .collect(Collectors.toList());
    }

    private String pprintDebug(Map<String, Integer> register) {
        StringBuilder keysb = new StringBuilder("Register: ");
        StringBuilder valsb = new StringBuilder("   Value: ");
        for (Map.Entry<String, Integer> e : register.entrySet()) {
            keysb.append("\t");
            keysb.append(String.format("%6s", e.getKey()));
            valsb.append("\t");
            valsb.append(String.format("%6d", e.getValue()));
        }
        return keysb.toString() + "\n" + valsb.toString();
    }

    @Override
    public Integer solvePart1() {
        Map<String, Integer> registers = new HashMap<>();

        for (Instruction instr : getInstructions()) {
            // System.out.println(pprintDebug(registers));
            Integer currentValue = registers.getOrDefault(instr.getOperand(), 0);
            Integer conditionalValue = registers.getOrDefault(instr.getConditionalRegister(), 0);
            if (instr.getConditionalComparison().compare(conditionalValue, instr.getConditionalAmount())) {
                currentValue += (instr.isIncrease() ? 1 : -1) * instr.getAmount();
                registers.put(instr.getOperand(), currentValue);
            }
        }
        return Collections.max(registers.values());
    }

    @Override
    public Integer solvePart2() {
        Map<String, Integer> registers = new HashMap<>();
        Integer overallMax = Integer.MIN_VALUE;

        for (Instruction instr : getInstructions()) {
            // System.out.println(pprintDebug(registers));
            Integer currentValue = registers.getOrDefault(instr.getOperand(), 0);
            Integer conditionalValue = registers.getOrDefault(instr.getConditionalRegister(), 0);
            if (instr.getConditionalComparison().compare(conditionalValue, instr.getConditionalAmount())) {
                currentValue += (instr.isIncrease() ? 1 : -1) * instr.getAmount();
                overallMax = Math.max(overallMax, currentValue);
                registers.put(instr.getOperand(), currentValue);
            }
        }
        return overallMax;
    }

    enum Comparison {
        LT,
        LE,
        EQ,
        NE,
        GE,
        GT,
        UNKNOWN;

        static Comparison parse(String symbol) {
            switch (symbol) {
                case "<":
                    return LT;
                case "<=":
                    return LE;
                case "==":
                    return EQ;
                case "!=":
                    return NE;
                case ">=":
                    return GE;
                case ">":
                    return GT;
                default:
                    return UNKNOWN;
            }
        }

        boolean compare(int x, int y) {
            switch (this) {
                case LT:
                    return x < y;
                case LE:
                    return x <= y;
                case EQ:
                    return x == y;
                case NE:
                    return x != y;
                case GE:
                    return x >= y;
                case GT:
                    return x > y;
                default:
                    return false;
            }
        }
    }

    static class Instruction {
        private final String operand;
        private final boolean increase;
        private final Integer amount;
        private final String conditionalRegister;
        private final Comparison conditionalComparison;
        private final Integer conditionalAmount;

        Instruction(
                String operand,
                boolean increase,
                Integer amount,
                String conditionalRegister,
                Comparison conditionalComparison,
                Integer conditionalAmount
        ) {
            this.operand = operand;
            this.increase = increase;
            this.amount = amount;
            this.conditionalRegister = conditionalRegister;
            this.conditionalComparison = conditionalComparison;
            this.conditionalAmount = conditionalAmount;
        }

        static Instruction parse(String text) {
            String[] parts = text.split(" if ");

            String[] instrParts = parts[0].split(" ");
            String operand = instrParts[0];
            boolean increase = Objects.equals(instrParts[1], "inc");
            Integer amount = Integer.parseInt(instrParts[2]);

            String[] condParts = parts[1].split(" ");
            String conditionalRegister = condParts[0];
            Comparison conditionalComparison = Comparison.parse(condParts[1]);
            Integer conditionalAmount = Integer.parseInt(condParts[2]);

            return new Instruction(
                    operand,
                    increase,
                    amount,
                    conditionalRegister,
                    conditionalComparison,
                    conditionalAmount
            );
        }

        String getOperand() {
            return operand;
        }

        boolean isIncrease() {
            return increase;
        }

        Integer getAmount() {
            return amount;
        }

        String getConditionalRegister() {
            return conditionalRegister;
        }

        Comparison getConditionalComparison() {
            return conditionalComparison;
        }

        Integer getConditionalAmount() {
            return conditionalAmount;
        }
    }
}
