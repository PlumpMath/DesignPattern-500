package com.book.headfirst.compoundpattern.combinedpattern;

/*
 * Adapter Pattern
 */
public class GooseAdapter implements Quackable
{
	private Goose goose;
	private DuckObservable observable;
	
	public GooseAdapter (Goose goose)
	{
		this.goose = goose;
		observable = new DuckSubject (this);
	}
	
	public void quack ()
	{
		goose.honk();
		notifyObservers();
	}	
	
	public String toString ()
	{
		return "Goose Adapter";
	}
	
	public void registerObserver (DuckObserver observer)
	{
		observable.registerObserver(observer);
	}
	
	public void notifyObservers ()
	{
		observable.notifyObservers();
	}
}

class Goose
{
	void honk ()
	{
System.out.println("Goose Honking");		
	}
}