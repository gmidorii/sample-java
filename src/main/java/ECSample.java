import org.eclipse.collections.api.list.ImmutableList;
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
	}
}
