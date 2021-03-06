package com.book.headfirst.proxypattern.dynamicproxy;

import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

import com.book.headfirst.proxypattern.dynamicproxy.invocationhandler.NonOwnerInvocationHandler;
import com.book.headfirst.proxypattern.dynamicproxy.invocationhandler.OwnerInvocationHandler;

public class DynamicProxyTest
{
	private static DBHelper dbHelper = new DBHelper ();
	public static void main(String[] args)
	{
//		PersonBean meher = dbHelper.getPersonBean("meher");
//System.out.println("Before updating Person Details Himself : " + meher);	
//		meher.setInterest("Sports, Hiking");
////		meher.setHotOrNot(2);
//		meher.setGender("Female");
//		dbHelper.editPerson("meher", meher);
//System.out.println("After updating Person Details Himself : " + dbHelper.getPersonBean("meher"));	

		PersonBean meher = dbHelper.getPersonBean("meher");
System.out.println("Before Updating Person Details By Others : " + meher);
		meher.setHotOrNot(5);
//		meher.setInterest("Hiking And Trails");
		dbHelper.editPerson("shami", meher);
System.out.println("After Updating Person Details By Others : " + dbHelper.getPersonBean("meher"));		
	}
}

class AccessRightsAuthenticator
{
	public boolean editPerson (String loginName, PersonBean personBeanBeforeUpdating, PersonBean toBeEditedPeronsBean)
	{
		PersonBean person = (loginName.equals(personBeanBeforeUpdating.getLoginName())) ? getOwnerInvocationHandler (personBeanBeforeUpdating) : getNonOwnerInvocationHandler(personBeanBeforeUpdating);
		try
		{
			if (! person.getName().equals(toBeEditedPeronsBean.getName()))
			{
				person.setName(toBeEditedPeronsBean.getName());
			}
			if (! person.getGender().equals(toBeEditedPeronsBean.getGender()))
			{
				person.setGender(toBeEditedPeronsBean.getGender());
			}
			if (! person.getInterest().equals(toBeEditedPeronsBean.getInterest()))
			{
				person.setInterest(toBeEditedPeronsBean.getInterest());
			}
			if (person.getHotOrNot() != toBeEditedPeronsBean.getHotOrNot())
			{
				person.setHotOrNot(toBeEditedPeronsBean.getHotOrNot());
			}
			return true;
		}
		catch (Exception exception)
		{
System.out.println("Sorry editing cannot be done");
exception.printStackTrace(System.err);
			return false;
		}
	}
	
	/*
	 * Proxy is generated by Java and implements the Subject interface.
	 * 
	 * Proxy passes on all the method calls to the InvocationHandler which controls the access to the methods of the RealSubject.  
	 */
	private PersonBean getOwnerInvocationHandler (PersonBean personBeanImpl)
	{
		return (PersonBean)Proxy.newProxyInstance(personBeanImpl.getClass().getClassLoader(), personBeanImpl.getClass().getInterfaces(), new OwnerInvocationHandler(personBeanImpl));
	}
	
	private PersonBean getNonOwnerInvocationHandler (PersonBean personBeanImpl)
	{
		return (PersonBean)Proxy.newProxyInstance(personBeanImpl.getClass().getClassLoader(), personBeanImpl.getClass().getInterfaces(), new NonOwnerInvocationHandler(personBeanImpl));
	}

}

class DBHelper
{
	private Map<String, PersonBean> personData = new HashMap<String, PersonBean>(3);
	
	DBHelper ()
	{
		initialiseData();
	}
	
	void initialiseData ()
	{
		personData.put("meher", populatePersonBean("meher", "Meher", "Male", "Sports", 9));
		personData.put("shami", populatePersonBean("shami", "Shami", "Female", "Cooking", 7));
		personData.put("baboo", populatePersonBean("baboo", "Baboo", "Male", "TV", 4));
	}
	
	public PersonBean getPersonBean (String loginName)
	{
		PersonBean dbPersonBean = personData.get(loginName);
		PersonBean personBean = new PersonBeanImpl ();
		personBean.setLoginName(dbPersonBean.getLoginName());
		personBean.setName(dbPersonBean.getName());
		personBean.setGender(dbPersonBean.getGender());
		personBean.setInterest(dbPersonBean.getInterest());
		personBean.setHotOrNot(dbPersonBean.getHotOrNot());
		return personBean;
	}
	
	public void editPerson (String loginName, PersonBean toBeUpdatedPersonBean)
	{
		//Apply Access Rights
		PersonBean peronBeanBeforeUpdating = getPersonBean(toBeUpdatedPersonBean.getLoginName());
		boolean isUpdatable = new AccessRightsAuthenticator ().editPerson(loginName, peronBeanBeforeUpdating, toBeUpdatedPersonBean);
		if (isUpdatable)
		{
			personData.put(toBeUpdatedPersonBean.getLoginName(), toBeUpdatedPersonBean);
		}		
	}
	
	private PersonBean populatePersonBean (String loginName, String name, String gender, String interest, int rating)
	{
		PersonBean personBean = new PersonBeanImpl ();
		personBean.setLoginName(loginName);
		personBean.setName(name);
		personBean.setGender(gender);
		personBean.setInterest(interest);
		personBean.setHotOrNot(rating);
		
		return personBean;
	}
}
