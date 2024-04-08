# 복잡한 과정 간소화

아래 코드에서 요약하는 것처럼 현재 filterApples 메서드로 새로운 동작을 전달하려면 ApplePredicate 인터페이스를 구현하는 여러 클래스를 정의한 다음에 인스턴스화해야 한다, 이는 상당히 번거로운 작업이며 시간 낭비다.

```
// 무거운 사과 선택
public class AppleHeavyWeightPredicate implements ApplePredicate {
  
  public boolean test(Apple apple) {
    return apple.getWeight() > 150;
  }
  
}
// 녹색 사과 선택
public class AppleGreenColorPredicate implements ApplePredicate {
  
  public boolean test(Apple apple) {
    return GREEN.equals(apple.getColor());
  }
  
}

public class FilteringApples {
  
  public static void main(String...args) {
    List<Apple> inventory = Arrays.asList(new Apple(80, GREEN), new Apple(155, GREEN), new Apple(120, RED));
    List<Apple> heavyApples = filterApples(inventory, new AppleHeavyWeightPredicate());
    // 155그램의 사과 한 개
    List<Apple> greenApples = filterApples(inventory, new AppleGreenColorPredicate());
    // 녹색 사과 두 개
  }

  public static List<Apple> filterApples(List<Apple> inventory, ApplePredicate p) {
    List<Apple> result = new ArrayList<>();
    for (Apple apple : inventory) {
      if (p.test(apple)){
        result.add(apple);
      }
    }
    return result;
  }
  
}
```

- 로직과 관련 없는 코드가 많이 추가되었습니다. 
- java는 클래스의 선언과 인스턴스화를 동시에 수행할 수 있도록 익명 클래스라는 기법을 제공합니다. 
- 익명 클래스를 사용하면 코드의 양을 줄일 수 있습니다.


## 다섯 번째 시도 : 익명 클래스 사용
익명 클래스는 자바의 지역 클래스와 비슷한 개념입니다. 익명 클래스는 말 그대로 이름이 없는 클래스입니다. 익명 클래스를 이용하면 클래스 선언과 인스턴스화를 동시에 할 수 있습니다.

```
// filterApples 메서드의 동작을 직접 파라미터화 했다.
List<Apple> redApples = filterApples(inventory, new ApplePredicate() {
  public boolean test(Apple apple){
    return RED.equals(apple.getColor());
  }
});
// 이벤트 핸들러 객체를 구현하는 익명 클래스
button.setOnAction(new EventHandler<ActionEvent>() {
  public void handle(ActionEvent event) {
    System.out.println("Whoooo a click!!");
  }
});

// 아래 2개의 코드는 반복되어 지저분한 코드...
List<Apple> redApples = filterApples(inventory, new ApplePredicate() {
  public boolean test(Apple a){
    return RED.equals(a.getColor());
  }
});

button.setOnAction(new EventHandler<ActionEvent>() {
  public void handle(ActionEvent event) {
    System.out.println("Whoooo a click!!");
}
```

코드의 장황함은 나쁜 특성입니다. 장황한 코드는 구현하고 유지보수하는 데 시간이 오래 걸릴 뿐 아니라 읽는 즐거움을 빼앗는 요소로 개발자로부터 외면받습니다.

지금까지 살펴본 것처럼 동작 파라미터화를 이용하면 요구사항 변화에 더 유연하게 대응할 수 있으므로 모든 프로그래머가 동작 파라미터화를 사용하도록 권장합니다. 우선 람다 표현식을 이용해서 어떻게 코드를 간결하게 정리할 수 있는지 간단히 살펴보겠습니다.


## 여섯 번째 시도 : 람다 표현식 사용

```
List<Apple> result = filterApples(inventory, (Apple apple) -> RED.equals(apple.getColor()));
```

## 일곱 번째 시도 : 리스트 형식으로 추상화

```
// 리스트 형식으로 추상화!
public interface Predicate<T> {
  boolean test(T t);
}

public static <T> List<T> filter(List<T> list, Predicate<T> p) { // 형식 파라미터 T
  List<T> result = new ArrayList<>();
  for(T e: list) {
    if(p.test(e)) {
      result.add(e);
    }
  }  
  return result;
}
// 람다 표현식을 이용해서 필터 메서드를 사용해보기
List<Apple> redApples = filter(inventory, (Apple apple) -> RED.equals(apple.getColor()));
List<Integer> evenNumbers = filter(numbers, (Integer i) -> i % 2 == 0);
```
이제 바나나, 오렌지, 정수, 문자열 등의 리스트에 필터 메서드를 사용할 수 있습니다. 
이렇게 해서 유연성과 간결함이라는 두 마리 토끼를 모두 잡았습니다.
