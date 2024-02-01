package org.example.week1;

import java.util.*;
import java.util.stream.Collectors;

public class Transaction {
    private int id;
    private int amount;
    private String currency;

    public Transaction(int id, int amount, String currency) {
        this.id = id;
        this.amount = amount;
        this.currency = currency;
    }

    public int getId() {
        return id;
    }

    public int getAmount() {
        return amount;
    }

    public String getCurrency() {
        return currency;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", amount=" + amount +
                ", currency='" + currency + '\'' +
                '}';
    }

    public static class CollectorProcessing {
        public static void main(String[] args) {
            List<Transaction> transactions = new ArrayList<>();

            transactions.add(new Transaction(1, 100, "USD"));
            transactions.add(new Transaction(2, 200, "USD"));
            transactions.add(new Transaction(3, 3000, "DKK"));
            transactions.add(new Transaction(4, 3500, "DKK"));

            System.out.println(transactions.stream()
                    .collect(Collectors.summarizingInt(Transaction::getAmount))
                    .getSum());

            Map<String, Integer> groupTransactions = transactions.stream()
                    .collect(Collectors.groupingBy(Transaction::getCurrency, Collectors.summingInt(Transaction::getAmount)));
                    groupTransactions.forEach((currency, sum) -> System.out.println(currency + sum));

            Optional<Transaction> highestAmount = transactions.stream()
                        .collect(Collectors.maxBy(Comparator.comparingInt(Transaction::getAmount)));

            highestAmount.ifPresent(transaction -> System.out.println("highest amount: " + transaction));

            Optional<Transaction> lowestAmount = transactions.stream()
                    .collect(Collectors.minBy(Comparator.comparingInt(Transaction::getAmount)));

            lowestAmount.ifPresent(transaction -> System.out.println("minimum amount: " + transaction));
        }
    }
}
