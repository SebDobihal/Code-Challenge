package codechallenge.utils;

import codechallenge.Shop;

public class ShopUtils {

    public static boolean compareShops(Shop shopA, Shop shopB) {
        return shopA.getAvailableBooks().equals(shopB.getAvailableBooks());
    }
}
