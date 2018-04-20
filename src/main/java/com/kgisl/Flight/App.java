package com.kgisl.Flight;


import java.awt.RenderingHints.Key;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.TreeSet;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;

public class App 
{
    public static void main( String[] args ) throws IOException
    {
       
        String fileName = "src/main/resource/Flight.csv";
        Path myPath = Paths.get(fileName);

        try (BufferedReader br = Files.newBufferedReader(myPath,
                StandardCharsets.UTF_8)) {

				
            HeaderColumnNameMappingStrategy<Flight> strategy
                    = new HeaderColumnNameMappingStrategy<>();
            strategy.setType(Flight.class);

            CsvToBean csvToBean = new CsvToBeanBuilder(br)
                    .withType(Flight.class)
                    .withMappingStrategy(strategy)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            List<Flight> emp = csvToBean.parse();
Map<String, Long> departureCounting = emp.stream()
.collect(Collectors.groupingBy(Flight::getDeparture, Collectors.counting()));
System.out.println(departureCounting);
//
Predicate<Flight> depatrurePredicate = x -> "Coimbatore".equals(x.getDeparture());
Predicate<Flight> arrivalPredicate = x -> "London".equals(x.getArrival());
System.out.println(emp.stream().filter(depatrurePredicate.and(arrivalPredicate)).map(x -> x.getId())
        .collect(Collectors.toList()));
        
		// Airlines name wise grouping
		Map<String, Set<String>> result = emp.stream()
        .collect(Collectors.groupingBy(Flight::getName, Collectors.mapping(Flight::toString, Collectors.toSet())));
System.out.println(result);
System.out.println("Duplicate:"+emp.stream().count());
///Unique value using toString().
Set<Flight> setFlight = emp.stream()
.collect(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(Flight::toString))));
//
Set<Flight> let = emp.stream()
.collect(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(Flight::getId)
.thenComparing(Comparator.comparing(Flight::getArrival)
.thenComparing(Comparator.comparing(Flight::getDeparture)
.thenComparing(Comparator.comparing(Flight::getName)))))));
System.out.println("id:  "+let);

System.out.println("///////////////////////////////////");
 HashSet<Flight> str=new HashSet<Flight>();
 HashSet<String> ids=new HashSet<>();
emp.stream()
    .forEach(p->{
        for (Flight f :emp) {
            if(p.getArrival().equals(f.getDeparture())&&p.getDeparture().equals(f.getArrival()))
            {   str.add(f);
                ids.add(f.getId());
                System.out.println(f);
        }  
        }
    }
    );
    System.out.println("*************************************");
     System.out.println(str+"\n"+ids);

     System.out.println("######################################");
     Map<String, Integer> mp=new HashMap<String,Integer>() ;
     ids.stream().forEach(p->{
         int k=0;
         for (Flight e : str) {
             if(e.getId().equals(p)){
                 k+=e.getAmount();
             }
         }
         mp.put(p, k);
     });
     
     System.out.println(mp);
     System.out.println("######################################");
    
     System.out.println("######################################");
     Map<String,List<Flight>> end=str.stream().collect(Collectors.groupingBy(Flight::getId));

     end.values().stream().forEach(p->System.out.println(p));
     System.out.println("sssdfsfdsfsfs"+end);
//      end.values().stream().filter(peopleWithSameId -> peopleWithSameId.size() > 1)
//      .forEach(peopleWithSameId -> System.out.println("People with identical IDs: " + peopleWithSameId));

// List<List<Flight>> result4 = end.values().stream()
//      .filter(peopleWithSameId -> peopleWithSameId.size() > 1).collect(Collectors.toList());






    //Set<Flight> st=emp.stream().collect(Collectors.toCollection(()->new TreeSet<Flight>(str)));
/*888888888888888888888888888888888888888888888888888888888888888
List<Flight> l=new ArrayList<>();
emp.stream()
.collect(Collectors.filter(k->{forEach(Flight p:emp)
    {
         for (Flight f :emp) {
             if(p.getArrival().equals(f.getDeparture())&&p.getDeparture().equals(f.getArrival())){
                    
                l.add(f);
             }
         }
            }
}).
groupingBy(Flight::getId)).values().stream()
.filter(flightWithSameId -> flightWithSameId.size() > 1)
.forEach(flightWithSameId-> System.out.println("Flights with identical IDs: " + flightWithSameId));
*/
}
}

}