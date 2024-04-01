# 자바 함수

프로그래밍 언어에서 함수function라는 용어는 메서드method 특히 정적 메서드static method와 같은 의미로 사용된다. 
자바의 함수는 이에 더해 수학적인 함수처럼 사용되며 부작용을 일으키지 않는 함수를 의미한다.


- 프로그래밍 언어에서 **함수**(function)라는 용어는 **메서드**(method) 특히 정적 메서드(Static method)와 같은 의미로 사용된다.
- 자바의 함수는 이에 더해 **수학적인 함수**처럼 사용되며 부작용을 일으키지 않는 함수를 의미한다.
- 객체(객체의 참조)도 값이다.
- new 또는 팩토리 메서드 또는 라이브러리 함수를 이용해서 객체의 값을 얻을 수 있다.
- 객체 참조는 클래스의 **인스턴스**(Instance)를 가리킨다.
- 프로그래밍 언어의 핵심은 값을 바꾸는 것이고 이 값을 일급 값 (또는 시민) 이라고 부른다.
- 자바 프로그래밍 언어의 다양한 구조체(메서드, 클래스 같은)가 값의 구조를 표현하는 데 도움될 수 있다.
- 메서드, 클래스 등은 이급 자바 시민에 해당한다.
- 만약 메서드와 클래스가 일급 시민으로 만들 수 있다면, 즉 런타임에 메서드를 전달할 수 있다면 프로그래밍에 유용하게 활용할 수 있다.


## 메서드와 람다를 일급 시민으로

메서드 참조method reference라는 새로운 자바 8의 기능


```
// 자바 8 이전
File[] hiddenFiles = new File(".").listFiles(new FileFilter() {
	public boolean accept(File file) {
		return file.isHidden(); // 숨겨진 파일 필터링
    }
})
```

```
// 자바 8 이후
File[] hiddenFiles = new File(".").listFiles(File::isHidden);

```

- isHidden이라는 함수(함수라는 용어를 사용했다는 것에 주목) 는 이미 준비되어 있으므로 자바 8의 메서드 참조 (method reference) :: ('이 메서드를 값으로 사용하라'의 의미)를 이용해서 listFiles 에 직접 전달할 수 있다. 

- 람다(익명함수) 예를 들어 (int a) -> a + 1, 즉 'a 라는 인수로 호출하면 a + 1을 반환'하는 동작을 수행하도록 코드를 구현할 수 있다.
이용할 수 있는 편리한 클래스나 메서드가 없을 때 람다 문법을 이용해 더 간결하게 코드를 구현 가능하다.



## 코드 넘겨주기 : 예제

Apple 클래스와 getColor 메서드가 있고, Apples 리스트를 포함하는 변수 inventory가 있다
고 가정하자. 이때 모든 녹색 사과를 선택해서 리스트를 반환하는 프로그램을 구현하려 한다.
이처럼 특정 항목을 선택해서 반환하는 동작을 필터filter라고 한다. 자바 8 이전에는 다음처럼
filterGreenApples라는 메서드를 구현했을 것이다.

```
public static List<Apple> filterGreenApples(List<Apple> inventory) {
    List<Apple> result = new ArrayList<>();
    for (Apple apple: inventory) {
        if (GREEN.equals(apple.getColor())) {
            result.add(apple);
        }
    } 
    return result;
}
```

하지만 누군가는 사과를 무게(예를 들면 150그램 이상)로 필터링하고 싶을 수 있다. 그러면
우리는 다음처럼 코드를 구현할 수 있을 것이다

```
public static List<Apple> filterHeavyApples(List<Apple> inventory) {
 List<Apple> result = new ArrayList<>();
    for (Apple apple: inventory) {
        if (apple.getWeight() > 150) {
        result.add(apple);
        }
    }
 return result;
}
```

자바 8 이후
```
class Test {
	public static boolean isGreenApple(Apple apple) {
		return GREEN.equals(apple.getColor());
    }

    public static boolean isHeavyApple(Apple apple) {
        return apple.getWeight() > 150;
    }
	
	public interface Predicate<T> {
		boolean test(T t);
    }
	
	static List<Apple> filterApples(List<Apple> inventory, Predicate<Apple> p) {
		List<Apple> result = new ArrayList<>();
		for (Apple apple : inventory) {
			if (p.test(apple)) {
				result.add(apple);
            }
        }
		return result;
    }
}
```

다음처럼 메소드 호출 할 수 있다.

```
filterApples(inventory, Apple::isGreenApple);
filterApples(inventory, Apple::isHeavyApple);
```


### 프레디케이트(predicate)란 무엇인가?

앞에서 다룬 예제에서는 Apple::isGreenApple 메서드를 filterApples로 넘겨주었다
(filterApples는 (Predicate<Apple>를 파라미터로 받음). 수학에서는 인수로 값을 받아 true
나 false를 반환하는 함수를 프레디케이트라고 한다. 나중에 설명하겠지만 자바 8에서도
Function<Apple, Boolean> 같이 코드를 구현할 수 있지만 Predicate<Apple>을 사용하는 것이
더 표준적인 방식이다(또한 boolean을 Boolean으로 변환하는 과정이 없으므로 더 효율적이기도
하다).



## 메서드 전달에서 람다로

메서드를 값으로 전달하는 것은 분명 유용한 기능이다. 하지만 isHeavyApple, isGreenApple처
럼 한두 번만 사용할 메서드를 매번 정의하는 것은 귀찮은 일이다. 자바 8에서는 이 문제도 간
단히 해결할 수 있다. 자바 8에서는 다음처럼 (익명 함수 또는 람다라는) 새로운 개념을 이용
해서 코드를 구현할 수 있다.

```
filterApples(inventory, (Apple a) -> GREEN.equals(a.getColor()) );
```

```
filterApples(inventory, (Apple a) -> a.getWeight() > 150 ); 
```

```
filterApples(inventory, (Apple a) -> a.getWeight() < 80 ||
 RED.equals(a.getColor()) ); 
```

한 번만 사용할 메서드는 따로 정의를 구현할 필요가 없다.

람다가 몇 줄 이상으로 길어진다면(즉, 조금 복잡한 동작을 수행하는 상황) 익명 람다
보다는 코드가 수행하는 일을 잘 설명하는 이름을 가진 메서드를 정의하고 메서드 참조를 활용
하는 것이 바람직하다. 코드의 명확성이 우선시되어야 한다.