/**
 * (C) 2014 TheMobies, LLC. All Rights Reserved. Confidential Information of TheMobies, LLC.
 */
package requests;

/**
 * @author Maggie
 * 
 */
public enum DistanceEnum
{
    ONE(new int[] { 0, 1 }), FIVE(new int[] { 0, 5 }), TEN(new int[] { 0, 10 }), MORE(
            new int[] { 0, 10000 });
    private final int[] range;

    private DistanceEnum(final int[] range)
    {
        this.range = range;
    }

    public int[] getRange()
    {
        return this.range;
    }
}
