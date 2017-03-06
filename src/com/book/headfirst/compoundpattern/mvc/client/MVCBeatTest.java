package com.book.headfirst.compoundpattern.mvc.client;

import com.book.headfirst.compoundpattern.mvc.controller.BeatController;
import com.book.headfirst.compoundpattern.mvc.controller.BeatControllerInterface;
import com.book.headfirst.compoundpattern.mvc.model.BeatModel;
import com.book.headfirst.compoundpattern.mvc.model.BeatModelInterface;

public class MVCBeatTest
{
	public static void main(String[] args)
	{
		BeatModelInterface beatModel = new BeatModel ();
		BeatControllerInterface beatController = new BeatController (beatModel);
	}
}
