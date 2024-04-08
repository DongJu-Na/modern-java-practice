# 실전 예제
이 절에서는 코드 전달 개념을 더욱 확실히 익힐 수 있도록 Comparator로 정렬하기, Runnable로 코드 블록 실행하기 예제를 소개해봅니다.

## Comparator로 정렬하기
컬렉션 정렬은 반복되는 프로그래밍 작업입니다. 개발자에게는 변화하는 요구사항에 쉽게 대응할 수 있는 다양한 정렬 동작을 수행할 수 있는 코드가 필요할 텐데 sort 메서드를 이용해서 알아보겠습니다.


```
// java.util.Comparator
public interface Comparator<T> {
int compare(T o1, T o2);
}
// 무게가 적은 순서로 목록에서 사과를 정렬할 수 있다!
inventory.sort(new Comparator<Apple>() {
  public int compare(Apple a1, Apple a2) {
    return a1.getWeight().compareTo(a2.getWeight());
  }
});

inventory.sort((Apple a1, Apple a2) -> a1.getWeight().compareTo(a2.getWeight())); // 람다 표현식을 이용하여 코드를 간단하게 구현한 모습
```


## Runnable로 코드 블록 실행하기
자바 스레드를 이용하면 병렬로 코드 블록을 실행할 수 있습니다.이 때 어떤 코드를 실행할 것인지를 스레드에게 알려주는 방법이 필요한데 자바 8까지는 Thread 생성자에 객체만을 전달할 수 있었으므로 보통 결과를 반환하지 않는 void run 메소드를 포함하는 익명 클래스가 Runnable 인터페이스를 구현하도록 하는 것이 일반적인 방법이었습니다.

자바에서는 Runnable 인터페이스를 이용해서 실행할 코드 블록을 지정할 수 있습니다. 아래 코드를 보겠습니다.

```
// java.lang.Runnable
public interface Runnable {
  void run();
}
// Runnable을 이용해서 다양한 동작을 스레드로 실행할 수 있다.
Thread t = new Thread(new Runnable() {
  public void run() {
    System.out.println("Hello world");
  }
});

Thread t = new Thread(() -> System.out.println("Hello world")); // 람다 표현식을 이용하여 코드를 구현한 모습

```