package Lab_7;

import java.util.List;
import java.util.Optional;

public class Search {

    /** Looks for the position of the named team in an array. */
    public static Optional<Integer> findTeamPosition(final Team[] arr, final String key) {
        // Gets the array size
        final int size = arr.length;
        // Runs through a for loop to check
        for (int i = 0; i < size; i++) {
            // Gets the current item at index and compare name to key
            if (arr[i].getName().equals(key)) {
                // Return the index of where the item with key is located
                return Optional.of(i);
            }
        }
        // If it does not exist in the array then return an index of -1
        return Optional.empty();
    }

    /** Looks for the position of the named team in a list. */
    public static Optional<Integer> findTeamPosition(final List<Team> list, final String key) {
        final int size = list.size();
        for (int i = 0; i < size; i++) {
            if (list.get(i).getName().equals(key)) {
                return Optional.of(i);
            }
        }
        return Optional.empty();
    }

    /**
     * Looks for the position of the poorest team that has at least
     * the specified funding level.
     * @pre arr is sorted by funding level
     * @post arr[result].funding >= minFunding && for all 0 <= i < result : arr[i].funding < minFunding
     */
    public static Optional<Integer> findTeamMinFunding(final Team[] arr, final int minFunding) {
        /* Linear search */
        final int size = arr.length;
        int low = 0;
        int min_val = -1;
        int i = 0;
        while (i < size) {
            if (arr[i].getFunding() >= minFunding) {
                if (min_val == -1) {
                    min_val = arr[i].getFunding();
                    low = i;
                }
                else if (arr[i].getFunding() < min_val) {
                    min_val = arr[i].getFunding();
                    low = i;
                }
            }
            i++;
        }
        if (min_val != -1)
            return Optional.of(low);
        else
            return Optional.empty();
    }

    /**
     * Looks for the position of the poorest team that has at least
     * the specified funding level.
     * Uses binary search: Initially consider the entire index range,
     * then repeatedly eliminate the wrong half of the array until
     * zero or one items are left.
     * @pre arr is sorted by funding level
     * @post arr[result].funding >= minFunding && for all 0 <= i < result : arr[i].funding < minFunding
     */
    public static Optional<Integer> findTeamMinFundingFast(final Team[] arr, final int minFunding) {
        final int size = arr.length;
        int low = 0;
        int high = size - 1;
        int mid;
        while (low <= high) {
            mid = (high + low) / 2;
            /* If the funding at pos mid is greater than the min required function, cut out the second half */
            if (arr[mid].getFunding() > minFunding) {
                high = mid;
            /* Else if the funding is less than what is required, set low equal to mid+1 */
            } else if (arr[mid].getFunding() < minFunding) {
                low = mid + 1;
            }
            /* If the low leq high and the low funding meets threshold, return pos low*/
            if (low <= high && arr[low].getFunding() >= minFunding) {
                return Optional.of(low);
            }
        }
        return Optional.empty();
    }
}
