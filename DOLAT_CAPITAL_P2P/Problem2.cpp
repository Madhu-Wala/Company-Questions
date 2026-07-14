#include <iostream>
#include <unordered_map>
#include <map>
using namespace std;

class Order
{
public:
    int orderId;
    int price;
    int quantity;
    Order(){}
    Order(int id,int p,int q)
    {
        orderId=id;
        price=p;
        quantity=q;
    }
};

class OrderBook
{
private:
    unordered_map<int,Order> orders;
    map<int,int,greater<int>> book;
public:
    void processNewOrder(int orderId,int price,int quantity)
    {
        orders[orderId]=Order(orderId,price,quantity);
        book[price]+=quantity;
    }

    void processModifyOrder(int orderId,int newPrice,int newQuantity)
    {
        if(!orders.count(orderId))
            return;

        Order &o=orders[orderId];
        book[o.price]-=o.quantity;

        if(book[o.price]==0)
            book.erase(o.price);

        o.price=newPrice;
        o.quantity=newQuantity;

        book[newPrice]+=newQuantity;
    }

    void processCancelOrder(int orderId)
    {
        if(!orders.count(orderId))
            return;
      
        Order o=orders[orderId];
        book[o.price]-=o.quantity;

        if(book[o.price]==0)
            book.erase(o.price);

        orders.erase(orderId);
    }

    void processTrade(int orderId,int tradedQuantity)
    {
        if(!orders.count(orderId))
            return;

        Order &o=orders[orderId];
        int actual=min(tradedQuantity,o.quantity);
        o.quantity-=actual;
        book[o.price]-=actual;

        if(book[o.price]==0)
            book.erase(o.price);

        if(o.quantity==0)
            orders.erase(orderId);
    }

    void printOrderBook()
    {
        for(auto &p:book)
        {
            cout<<p.first<<" "<<p.second<<endl;
        }
    }
};
