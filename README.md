# Supermarkets

The applications you write will nearly always dealing with data in some form. The more ways you know to organise and store data in an application the more robust and effective code you'll be able to write. In this workshop we are going to learn about 4 data structures that take us beyond the limitations of the humble Array. By the end of this workshop you should have a few more Java tricks up your developer sleave!

## You will be learning to use

* ArrayList
* HashMap
* Stack
* ArrayDeque

## Abstraction

Do you remember the way we created a `Room` class in the Hotels workshop? We ended up turning the `Room` class into an abstract class. Being an abstract class meant we never used the class to create objects in our code, instead it became a repository of state and methods that we then shared via inheritance with our sub-classes `SingleRoom`, `DoubleRoom` etc.

## Interfaces

In Java you can take the idea of an abstract class to the next level by defining an `Interface`. This is then next level of abstraction. Our `Room` class had properties and methods defined within it. An interface would just have placeholder properties and methods.

```java
interface RoomInterface {
    private int number;
    private String type = "Double";
    private Guest[] beds = {null, null};

    public boolean isEmpty () {}

    public int getNumber() {}

    public void addGuest(Guest guest) {}

    public Guest removeGuest() {}
}
```
We might then use the `interface` like this:
```java
class Room implements RoomInterface {
    // class definition in here
}
```
Now it would be upto us to implement all those properties and methods. How our version of `addGuest` works within our `Room` class is upto us - but our code will not compile unless we honor the contract and implement the interface completely. Interfaces are another level of abstraction above abstract classes. They define a shared set of properties and methods among the classes and sub-classes that implement the interface or inherit from classes that do.

Today there are 3 interfaces that behave like our `RoomInterface` class that would be helpful to learn about first. They are:

1. Map
1. List
1. Queue

### Map

|Key|Value|
|:--|:----|
|001|Bread|
|002|Cheese|
|003|Wine|

A Map store things in key values pairs. The idea of this data structure is we can look things up really quickly and easily. For example what is `003`?

![Map interface](https://user-images.githubusercontent.com/4499581/150323480-7ff70da7-f427-4849-a45a-9458acc578c5.png)

There are different types of Map, but you know you can call `getOrDefault(K, defaultValue)` on HashMap, TreeMap, WeakHashMap etc. it will work on all of them.

You will use HashMap!

### List

```java
[ Item, Item, Item ]
```

A list is an array, but without the limitation of having to declare upfront how many items will be in the array. The List is a very useful data structure that enables us to add and remove items as our program requires.

![List interface and classes](https://user-images.githubusercontent.com/4499581/150323478-6f4b56d8-103d-48b8-9bc3-c6cdc7b1a5f8.png)

There are structures that are a variation on List, but they will all respond to `contains()`. So we can call `contains` on an ArrayList, LinkedList, Stack or Vector. 

In the Stack structure items are put on the stack then taken off it in order. It is like putting things in your shopping trolly, the first items you put in are the last ones to come out.

![Stack](https://miro.medium.com/max/1280/0*SESFJYWU5a-3XM9m.gif "https://dev.to/adavidoaiei/fundamental-data-structures-and-algorithms-in-c-4ocf")

You will be using ArrayList and Stack!

### Queue

A queue will let us add and remove items from a queue. It means we can deal with things in order, like shoppers queue to checkout their shopping one at a time.

![Queue](https://user-images.githubusercontent.com/4499581/150323483-8259d588-56a7-430c-b234-4f8165523b98.png "Multiverse")

These structures are amazing at organising a series of tasks, or things that need to be processed but can't all be done at the same time.

![Que](https://1.bp.blogspot.com/-N-v_FiIdQXM/XlkFCQQYtPI/AAAAAAAAHR0/zxkuX6WfQS8Y8Mkoj1nHZDWtMOD3MjsUwCLcBGAsYHQ/s1600/0_E33E-AjyAUTFjVmM.gif "https://dev.to/adavidoaiei/fundamental-data-structures-and-algorithms-in-c-4ocf")

You will use ArrayDeque!

### Collections

Finally for List and Queue there is another level of shared functionality as they both implement the `Collection` interface.

![Collections interface](https://user-images.githubusercontent.com/4499581/150324442-cc4e63d1-b3c0-4511-9001-6ae9a51d4733.png)

## Let's code!

First of all create a supermarket.
```java
public class Supermarket {
    public Supermarket() {
        // we can code in here
    }
    public static void main(String[] ___) {
        new Supermarket();
    }
}
```
Why do we go to the supermarket? To get products. Can you make a `Product` class, that has the description of the product as a `String` and the recommended retail price (RRP) as a `double`. We also want some like of barcode so we can keep track of our product. Can you see below I'm getting the `hashCode` of the product's description (desc) and returning that. The `hashCode` of a string will always result in the same numerical value. You will see why we might want that in a minuet.

```java
public class Product {
    private String desc;
    private double RRP;

    public Product(String desc, double RRP) {
        this.desc = desc;
        this.RRP = RRP;
    }

    public String getDesc() {
        return this.desc;
    }

    public double getPrice() {
        return this.RRP;
    }

    public int getBarcode() {
        return new String(this.desc).hashCode();
    }
}
```
### Inventory

In our supermarket we can start our inventory. If we use an array of items.
```java
Product[] products = new Product[?];
```
We would have to state what the size of the array is. This is a pain for us because we don't know ahead of time how many products we might have in our inventory. It's likely over time we'll want to add new products to our supermarket. What we really want is an array we can just add things to, that will grow and shrink. For this we need `ArrayList`.
```java
    private ArrayList<Product> products = new ArrayList<>();
```
You will have to import `ArrayList` because we are now reaching for things outside the default `java.lang` package. At the top of your file add.
```java
import java.util.ArrayList;
```
Now we have an few methods on ArrayList we can use to organise our products; Below are examples of the create, read, update, delete (CRUD) method calls.
```java
    // CREATE
    products.add(new Product("Coffee", 2.79));
    products.add(new Product("Sugar", 1.20));
    products.add(new Product("Tea", 2.50));
    products.add(new Product("Rum", 12.79));
    // READ
    products.size() // 4
    products.get(0) // Product@hashCode
    // UPDATE
    products.set(3, new Product("Rum", 13.50));
    // Destroy
    products.remove(3)
    products.size() // 3
    products.clear()
    products.size() // 0
```
The ArrayList is helping us to organise our inventory. The next thing to think about are the stock levels.

### Stock levels

In our supermarket we will want to keep track of the stock levels we have for our inventory. When an item is purchased at the checkout, we should decrement our stock levels and ensure we order more of that item when stocks are low. This is were our barcode comes in.

At the checkout we scan a product and read the barcode. We can use the barcode as a key to look up the stock level for that product, and decrement it. In the body of the `Supermarket` class add the stock property.

```java
    private HashMap<Integer, Integer> stock = new HashMap<>();
```
Then in our constructor function once you have added all your inventory items you can iterate over them and add a key, value pair (the barcode is the key, the value is the initial stock level of 10).
```java
        inventory.add(new Product("Bread", 0.56));
        inventory.add(new Product("Coffee", 2.69));
        inventory.add(new Product("Tea", 2.39));
        for(Product product : inventory) {
            //   .put(        key         ,   value  );
            stock.put(product.getBarcode(),    10    );
        }
```
At this point we have used 2 new data structures, ArrayList and HashMap.

### Shopper

We are ready to go shopping. Let us create a `Shopper` class to represent our shopper, the most important property they will own is their trolly. We want the shopper to be able to add products into there trolly and then at the checkout remove them one by one. In the real world items placed in the trolly first are at the bottom, additional items are placed on top. At the checkout the last products placed in the trolly are the first ones to be scanned. Sounds like a Stack to me!
```java
import java.util.Stack;

public class Shopper {
    private Stack<Product> trolly = new Stack<>();

    public void addProduct(Product product) {
        trolly.push(product);
    }

    public Product getProduct() {
        return trolly.pop();
    }

    public boolean hasProducts() {
        return !trolly.isEmpty();
    }
}
```
### Let's go shopping

You can now create a couple of shoppers and go shopping. My supermarket looks something like this:
```java
import java.util.ArrayList;
import java.util.HashMap;

public class Supermarket {
    private HashMap<Integer, Integer> stock = new HashMap<>();
    private ArrayList<Item> inventory = new ArrayList<>();

    public Supermarket() {
        inventory.add(new Item("Bread", 0.56));
        inventory.add(new Item("Coffee", 2.69));
        inventory.add(new Item("Tea", 2.39));
        for(Item item : inventory) {
            stock.put(item.getBarcode(), 10);
        }
        Shopper shopper = new Shopper();
        Shopper shopper2 = new Shopper();
        shopper.addItem(inventory.get(0));
        shopper.addItem(inventory.get(0));
        shopper.addItem(inventory.get(2));
        shopper2.addItem(inventory.get(0));
        shopper2.addItem(inventory.get(1));
        shopper2.addItem(inventory.get(2));
    }
    public static void main(String[] ___) {
        new Supermarket();
    }
}
```

### Queue at the checkout

Our shoppers are ready to buy their products. Usually in a supermarket when you are ready to leave and pay you queue at the checkout. Each shopper's shopping is then processed one shopper at a time. Shoppers join the queue at the back, the last position. Shoppers at the front of the queue get to process their shopping. Sounds like a Queue to me!

Add a checkout to your `Supermarket` class. We'll use the ArrayDeque.
```java
private ArrayDeque<Shopper> checkout = new ArrayDeque<>();
```
Can you add your shoppers to the checkout (back of the queue!)? You can use either `add()` or `addLast()` they both insert the specified element at the end of the array deque.
```java
checkout.addLast(shopper1);
checkout.addLast(shopper2);
```

### Process our shoppers

Now we have a queue at our checkout we need to write the code to process our shoppers. As we just want to keep working while there are shoppers, we should leverage the while loop.
```java
    private void processShoppers() {
        while(!checkout.isEmpty()) {
            Shopper shopper = checkout.pollFirst();
            double total = 0;
            while(shopper.hasItems()) {
                Item item = shopper.getItem();
                total += item.getPrice();
                int currentStock = stock.get(item.getBarcode());
                stock.replace(item.getBarcode(), currentStock - 1);
            }
            System.out.printf("Shopper %d total: £%.f2\n", shopper.hashCode(), total);
        }
    }
```
In the code above what is the first while condition? How does that become false?
What does `pollFirst` do that is different to `getFirst`?

## Assignment

We have learnt about the hierarchy of the collections framework, and how interfaces work in Java. You have used 4 different Java utilities to organise and work with data.

1. ArrayList
1. HashMap
1. Stack
1. ArrayDeque

You have used 2 Packages from the `java.base` module.

|Package|Description|
|:-----|:----------|
|`java.lang`|Provides classes that are fundamental to the design of the Java programming language. The most important classes are Object, which is the root of the class hierarchy, and Class, instances of which represent classes at run time.|
|`java.util`|Contains the collections framework, some internationalization support classes, a service loader, properties, random number generation, string parsing and scanning classes, base64 encoding and decoding, a bit array, and several miscellaneous utility classes. This package also contains legacy collection classes and legacy date and time classes|

Take a little time to read and explore the Oracle documentation. Can you find the documentation for ArrayList, HashMap, Stack and ArrayDeque? Read through some of the descriptions until you have a sense of what is available. You do not need to memorize or fully understand every class in every package to learn Java. Knowing what is available is enough at this stage. When it's time to reach for something (like reading a file off of disk `java.io`) you can go to the documentation and find out how to use that part of the language.

## [Supermarket walk-though video](https://www.loom.com/embed/8e57fb75e2224b52b12f5c36a233b9e2)
