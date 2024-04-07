# 변화하는 요구사항에 대응하기

- 변화하는 요구사항은 소프트웨어 엔지니어링에서 피할 수 없는 문제다.
- 동작 파라미터화behavior parameterization를 이용하면 자주 바뀌는 요구사항에 효과적으로 대응할 수 있다.

## 첫 번째 시도 : 녹색 사과 필터링

기존의 농장 재고목록 애플리케이션에 리스트에서 녹색green 사과만 필터링하는 기능을 추가한다고 가정하자.
```
enum Color { RED, GREEN }

public static List<Apple> filterGreenApples(List<Apple> inventory) {
    List<Apple> result = new ArrayList<>(); // 사과 누적 리스트
    for (Apple apple: inventory) {
        if (GREEN.equals(apple.getColor()) {  // 녹색 사과만 선택
            result.add(apple);
        }
    }
    return result;
}
```

## 두 번째 시도 : 색을 파라미터화

```
public static List<Apple> filterApplesByColor(List<Apple> inventory, Color color){
 List<Apple> result = new ArrayList<>();
 for (Apple apple: inventory) {
    if ( apple.getColor().equals(color) ) {
        result.add(apple);
    }
 }
 return result;
}
```

```
    List<Apple> greenApples = filterApplesByColor(inventory, GREEN); List<Apple>
    redApples = filterApplesByColor(inventory, RED);
```


```
public static List<Apple> filterApplesByWeight(List<Apple> inventory, int weight){
 List<Apple> result = new ArrayList<>();
 for (Apple apple: inventory) {
    if ( apple.getWeight() > weight ) {
        result.add(apple);
    }
 }
 return result;
}
```



## 세 번째 시도 : 가능한 모든 속성으로 필터링

```
public static List<Apple> filterApples(List<Apple> inventory, Color color,int weight, boolean flag) {
 List<Apple> result = new ArrayList<>();
 for (Apple apple: inventory) {
    if ((flag && apple.getColor().equals(color)) ||
    (!flag && apple.getWeight() > weight)) {
        result.add(apple);
    }
 }
 return result;
}
```


```
    List<Apple> greenApples = filterApples(inventory, GREEN, 0, true);
    List<Apple> heavyApples = filterApples(inventory, null, 150, false);
```

