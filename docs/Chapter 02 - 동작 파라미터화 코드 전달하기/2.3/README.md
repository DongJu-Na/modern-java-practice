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