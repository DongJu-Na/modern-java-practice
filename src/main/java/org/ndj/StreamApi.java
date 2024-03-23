package org.ndj;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamApi {

    public static void main(String[] args) {

        List<String> names = Arrays.asList("John", "Doe", "Jane", "Alex", "Alice");
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

        /*******************************************************************
         * old way
         ********************************************************************/

        //Filtering - 데이터 컬렉션에서 특정 조건을 만족하는 요소들만 선택
        List<String> oldFilteredNames = new ArrayList<>();
        for (String name : names) {
            if (name.startsWith("J")) {
                oldFilteredNames.add(name);
            }
        }

        System.out.println(oldFilteredNames);

        //Mapping - 데이터 컬렉션의 요소를 다른 형태로 변환
        List<Integer> oldNameLengths = new ArrayList<>();
        for (String name : names) {
            oldNameLengths.add(name.length());
        }

        System.out.println(oldNameLengths);

        //Sorting - 데이터 컬렉션의 요소를 정렬
        List<String> oldSortedNames = new ArrayList<>(names);
        oldSortedNames.sort(Comparator.naturalOrder());

        System.out.println(oldSortedNames);

        //Grouping - 데이터 컬렉션의 요소를 특정 기준으로 그룹화
        Map<Integer, List<String>> oldNamesByLength = new HashMap<>();
        for (String name : names) {
            int length = name.length();
            List<String> group = oldNamesByLength.getOrDefault(length, new ArrayList<>());
            group.add(name);
            oldNamesByLength.put(length, group);
        }

        System.out.println(oldNamesByLength);

        //Reducing - 데이터 컬렉션의 모든 요소를 하나로
        int oldSum = 0;
        for (int num : numbers) {
            oldSum += num;
        }

        System.out.println(oldSum);

        //Processing - 데이터 컬렉션을 병렬로 처리
        int oldSumValue = 0;
        for (int num : numbers) {
            oldSumValue += num;
        }

        System.out.println(oldSumValue);

        //Stream Concatenation - 두 개의 스트림을 연결
        List<String> oldConcatenatedList = new ArrayList<>();
        List<String> oldFirstNames = Arrays.asList("John", "Doe");
        List<String> oldLastNames = Arrays.asList("Smith", "Johnson");
        oldConcatenatedList.addAll(oldFirstNames);
        oldConcatenatedList.addAll(oldLastNames);
        System.out.println(oldConcatenatedList);


        /*******************************************************************
         * new way Stream API Use
         ********************************************************************/

        //Filtering - 데이터 컬렉션에서 특정 조건을 만족하는 요소들만 선택
        List<String> filteredNames = names.stream()
                .filter(name -> name.startsWith("J"))
                .toList(); // collect(Collectors.toList()) same Code 같은 동작화는 코드

        System.out.println(filteredNames.toString());

        //Mapping - 데이터 컬렉션의 요소를 다른 형태로 변환
        List<Integer> nameLengths = names.stream()
                .map(String::length)
                .toList();

        System.out.println(nameLengths);

        //Sorting - 데이터 컬렉션의 요소를 정렬
        List<String> sortedNames = names.stream()
                .sorted()
                .toList();

        System.out.println(sortedNames.toString());

        //Grouping - 데이터 컬렉션의 요소를 특정 기준으로 그룹화
        Map<Integer, List<String>> namesByLength = names.stream()
                .collect(Collectors.groupingBy(String::length));

        System.out.println(namesByLength.toString());

        //Reducing - 데이터 컬렉션의 모든 요소를 하나로
        Optional<Integer> sum = numbers.stream()
                .reduce(Integer::sum);

        System.out.println(sum.toString());

        //Processing - 데이터 컬렉션을 병렬로 처리
        int sumValue = numbers.parallelStream()
                .mapToInt(Integer::intValue)
                .sum();

        System.out.println(sumValue);

        //Stream Concatenation - 두 개의 스트림을 연결
        List<String> firstNames = Arrays.asList("John", "Doe");
        List<String> lastNames = Arrays.asList("Smith", "Johnson");
        Stream<String> concatenatedStream = Stream.concat(firstNames.stream(), lastNames.stream());

        System.out.println(concatenatedStream);


    }

}
