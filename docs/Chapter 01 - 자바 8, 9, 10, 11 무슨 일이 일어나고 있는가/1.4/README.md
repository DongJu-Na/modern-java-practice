# 스트림

- 거의 모든 자바 애플리케이션은 **컬렉션**을 만들고 활용한다.
- 하지만 컬렉션으로 모든 문제가 해결되는 것은 아니다.

예를 들어 리스트에서 고가의 트랜잭션Transaction (거래)만 필터링한 다음에 통화로 결과를 그룹화해야 한다고 가정하자. 다음 코드처럼 많은 기본 코드를 구현해야 한다.

```
Map<Currency, List<Transaction>> transactionsByCurrencies =new HashMap<>(); // 그룹화된 트랜잭션을 더할 Map 생성
    for (Transaction transaction : transactions) { // 트랜잭션의 리스트를 반복
        if (transaction.getPrice() > 1000) {  // 고가의 트랜잭션을 필터링
                Currency currency = transaction.getCurrency();  // 트랜잭션의 통화 추출
                List<Transaction> transactionsForCurrency =
                transactionsByCurrencies.get(currency);
            if (transactionsForCurrency == null) {
                transactionsForCurrency = new ArrayList<>();
                transactionsByCurrencies.put(currency,
                transactionsForCurrency);
            }
            transactionsForCurrency.add(transaction);
        }
    }
```

- 중첩된 제어 흐름 문장이 많아서 코드를 한 번에 이해하기도 어렵다.
- 스트림 API를 이용하면 다음처럼 문제를 해결할 수 있다.

```
import static java.util.stream.Collectors.groupingBy;
    Map<Currency, List<Transaction>> transactionsByCurrencies = 
    transactions.stream()
  .filter((Transaction t) -> t.getPrice() > 1000)  // 고가의 트랜잭션 필터링
 .collect(groupingBy(Transaction::getCurrency)); // 통화로 그룹화함
```

## 컬렉션
- for-each루프를 이용해 반복 과정을 직접 처리함. -> 외부 반복
- 기본적으로 단일 CPU만 이용하여 순차적으로 처리한다.
 

## 스트림
- 라이브러리 내부에서 수행함. -> 내부 반복
- 기본적으로 멀티 CPU를 이용하여 병렬로 처리한다.