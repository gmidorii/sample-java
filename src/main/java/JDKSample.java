import com.google.common.collect.Lists;

import java.util.EnumSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by midori on 2017/02/16.
 */
public class JDKSample {
	public void samples() {
		List<Integer> listInt = Lists.newArrayList(
				1, 2, 3, 4, 5, 6, 7, 8, 9, 10
		);
		List<String> listStr = Lists.newArrayList(
				"1", "2", "3", "4", "5"
		);
		List<String> listStr2 = Lists.newArrayList(
				"6", "7", "8", "9", "10"
		);

		Predicate<Integer> biggerThanZero = x -> x > 0;
		Predicate<Integer> biggerThanFive = x -> x > 5;

		/**
		 * allMatch(Predicate<T>)
		 */
		System.out.println(
				listInt.stream().allMatch(biggerThanZero) //true
		);
		System.out.println(
				listInt.stream().allMatch(biggerThanFive) //false
		);

		/**
		 * anyMatch(Predicate<T>)
		 */
		System.out.println(
				listInt.stream().anyMatch(x -> x > 3) // true
		);

		/**
		 * collect(Collector<? super T,A,R> collector)
		 */
		System.out.println(
				listStr.stream().collect(Collectors.joining()) // 12345
		);
		System.out.println(
				listStr.stream().collect(new CommaJoiner()) // 1.2.3.4.5.
		);

		/**
		 * concat(Stream<? extends T> a, Stream<? extends T> b)
		 */
		printStream(Stream.concat(listStr.stream(), listStr2.stream())); // 1 2 3 4 5 6 7 8 9 10

		/**
		 * count()
		 */
		System.out.println(
				listStr.stream().count() // 5
		);

		/**
		 * distinct()
		 */
		List<String> listDup = Lists.newArrayList(
				"1", "1", "2", "3", "3"
		);
		printStream(listDup.stream().distinct()); // 1 2 3

		/**
		 * empty()
		 */
		Stream empty = Stream.empty();
		System.out.println(empty.count()); // 0

		/**
		 * filter(Predicate<? super T> predicate)
		 *
		 */
		printStream(listInt.stream().filter(x -> x > 5)); // 6 7 8 9 10

		List<Train> trains = Lists.newArrayList(
				new Train("1"), new Train("2", "nozomi"), new Train("3")
		);
		// method reference
		printStream(trains.stream().filter(Train::hasName)); // Train{name='nozomi'}

		/**
		 * findAny()
		 */
		Optional<Integer> num = listInt.stream().findAny();
		num.ifPresent(x -> System.out.println(x)); // random

		/**
		 * findFirst()
		 */
		Optional<Integer> first = listInt.stream().findFirst();
		num.ifPresent(x -> System.out.println(x)); // 1

		/**
		 * flatMap(Function<? super T,? extends Stream<? extends R>> mapper)
		 * XXX one-to-many transformation
		 */
		Train t1 = new Train("1")
				.appendCourse(1)
				.appendCourse(2)
				.appendCourse(3)
				.appendCourse(4);
		Train t2 = new Train("2")
				.appendCourse(1)
				.appendCourse(2)
				.appendCourse(3)
				.appendCourse(4);
		List<Train> trains1 = Lists.newArrayList(t1, t2);
		printStream(trains1.stream().flatMap(new Function<Train, Stream<Integer>>() {
			@Override
			public Stream<Integer> apply(Train s) {
				return s.getCourses().stream();
			}
		})); // 1 2 3 4 1 2 3 4

		/**
		 * flatMapToDouble(Function<? super T,? extends DoubleStream> mapper)
		 */
		Train td1 = new Train("1")
				.appendDistance(1.0)
				.appendDistance(2.0)
				.appendDistance(3.0)
				.appendDistance(4.0);
		Train td2 = new Train("2")
				.appendDistance(1.0)
				.appendDistance(2.0)
				.appendDistance(3.0)
				.appendDistance(4.0);
		List<Train> trains2 = Lists.newArrayList(td1, td2);
		printStream(trains2.stream().flatMap(new Function<Train, Stream<Double>>() {
			@Override
			public Stream<Double> apply(Train s) {
				return s.getDistances().stream();
			}
		})); // 1.0 2.0 3.0 4.0 1.0 2.0 3.0 4.0
	}

	private <T> void printStream(Stream<T> stream) {
		stream.forEach(x -> {
					System.out.print(x);
					System.out.print(" ");
				}
		);
		System.out.println();
	}

	private class CommaJoiner implements Collector<String, StringBuilder, String> {

		// create temporarily saved object
		@Override
		public Supplier<StringBuilder> supplier() {
			return StringBuilder::new;
		}

		// processing
		@Override
		public BiConsumer<StringBuilder, String> accumulator() {
			return (sb, s) -> {
				sb.append(s);
				sb.append(".");
			};
		}

		// for parallel
		@Override
		public BinaryOperator<StringBuilder> combiner() {
			return (sb1, sb2) -> {
				sb1.append(sb2);
				return sb1;
			};
		}

		// convert result type
		@Override
		public Function<StringBuilder, String> finisher() {
			return sb -> sb.toString();
		}

		// ?
		@Override
		public Set<Characteristics> characteristics() {
			return EnumSet.noneOf(Characteristics.class);
		}
	}
}
