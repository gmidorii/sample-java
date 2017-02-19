import org.eclipse.collections.api.list.ImmutableList;
import org.eclipse.collections.api.partition.list.PartitionImmutableList;
import org.eclipse.collections.impl.factory.Lists;
import org.eclipse.collections.impl.list.mutable.FastList;

import java.util.List;

import static org.eclipse.collections.impl.factory.Lists.immutable;

/**
 * Created by midori on 2017/02/16.
 */
public class ECSample {

	public void samples() {

		/**
		 * select(Predicate<? super T> predicate)
		 */
		immutable
				.of(1, 2, 3, 4, 5)
				.select(x -> x > 3)
				.forEach(System.out::print);
		System.out.println();

		// ============================
		// List
		// ============================
		List<Integer> l1 = FastList.newList();
		List<Integer> l2 = FastList.newListWith(1, 2, 3, 4);
		List<Integer> l3 = Lists.mutable.of(1, 2, 3);
		// Type must be ImmutableList<>
		ImmutableList<Integer> l4 = Lists.immutable.of();

		/**
		 * reject(Predicate<? super T> predicate)
		 */
		immutable
				.of(1, 2, 3, 4, 5)
				.reject(x -> x > 3)
				.forEach(System.out::print); //123
		newLine();

		/**
		 * partition(Predicate<? super T> predicate)
		 */
		PartitionImmutableList<Integer> partitionList =  immutable
				.of(1, 2, 3, 4, 5)
				.partition(x -> x > 3);
		System.out.println(partitionList.getSelected()); // [4, 5]
		System.out.println(partitionList.getRejected()); // [1, 2, 3]

		/**
		 * collect(Function<? super T,? extends V> function)
		 */
		immutable
				.of("one", "two", "three", "four", "five")
				.collect(x -> x.length())
				.forEach(System.out::print); //33544
		newLine();
		System.out.println(
				createTrains()
					.collect(t -> t.getName()) // [nozomi, hikari, yamanote, ginza]
		);
		createTrains()
			.collect(t -> {
				String name = t.getName();
				return new Train(name);
			})
			.forEach(x -> {
					System.out.print(x.getId());
					System.out.print(" ,");
			}); // nozomi ,hikari ,yamanote ,ginza ,
		newLine();

		/**
		 * flatCollect(Function<? super T,? extends Iterable<V>> function)
		 * XXX jdk -> flatMap
		 */
		immutable
				.of(createTrains(),
						createTrains())
				.flatCollect(ts -> ts.collect(t -> t.getId()))
				.forEach(System.out::print); //12341234
	}

	private void newLine() {
		System.out.println();
	}

	private ImmutableList<Train> createTrains() {
		return immutable
				.of(new Train("1", "nozomi"),new Train("2", "hikari"),
						new Train("3", "yamanote"),new Train("4", "ginza"));
	}
}