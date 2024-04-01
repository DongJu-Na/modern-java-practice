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



# 멀티스레딩은 어렵다

- 이전 자바 버전에서 제공하는 스레드 API로 멀티스레딩 코드를 구현해서 병렬성을 이용하는 것은 쉽지 않다. 
- 멀티스레딩 환경에서 각각의 스레드는 동시에 공유된 데이터에 접근하고, 데이터를 갱신할 수 있다. 
- 결과적으로 스레드를 잘 제어하지 못하면 원치 않는 방식으로 데이터가 바뀔 수 있다.
- 멀티스레딩 모델은 순차적인 모델보다 다루기가 어렵다.

책에 있는 그림을 같이 보면 이해가 쉽다!

- 자바 8은 컬렉션을 처리하면서 발생하는 모호함과 반복적인 코드 문제 그리고 멀티코어 활용 어려움을 해결하기 위해 스트림 API를 도입했다.


## 자바의 병렬성과 공유되지 않은 가변 상태

-   흔히 자바의 병렬성은 어렵고, synchronized는 쉽게 에러를 일으킨다고 생각한다.
-   자바 8은 이를 해결하기 위해 두가지 방식을 사용한다.
    -   라이브러리에서 분할 처리를 한다. 큰 스트림을 병렬 처리할 수 있도록 작은 스트림으로 분할한다.
    -   filter 같은 라이브러리 메서드로 전달된 메서드가 상호작용하지 않는다면 가변 공유 객체를 통해 병렬성을 누릴 수 있다.  
        -> 여러 스레드가 동시에 가변 객체에 접근하더라도, 메서드가 상호작용하지 않기 때문에 안전하지만, 만약 전달되는 메서드가 공유되는 가변 객체를 변경한다면 문제가 발생한다. 이런 경우에는 스레드 동기화 기법을 사용하여 해결해야 한다.
-   함수형 프로그래밍에서 함수형이란 '함수를 일급 값으로 사용한다.'는 의미를 가지고 있지만 부가적으로 '프로그램이 실행되는 동안 컴포넌트 간에 상호작용이 일어나지 않는다' 라는 의미도 포함한다.
