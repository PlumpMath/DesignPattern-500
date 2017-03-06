package com.book.headfirst.compoundpattern.mvc.client;

import com.book.headfirst.compoundpattern.mvc.controller.BeatController;
import com.book.headfirst.compoundpattern.mvc.controller.BeatControllerInterface;
import com.book.headfirst.compoundpattern.mvc.model.BeatModelInterface;
import com.book.headfirst.compoundpattern.mvc.model.HeartModel;
import com.book.headfirst.compoundpattern.mvc.model.HeartModelAdapter;
import com.book.headfirst.compoundpattern.mvc.model.HeartModelInterface;

public class MVCHeartTest
{
	public static void main(String[] args)
	{
		HeartModelInterface heartModel = new HeartModel ();
		BeatModelInterface beatModel = new HeartModelAdapter (heartModel);
		BeatControllerInterface beatController = new BeatController (beatModel);
	}
}
