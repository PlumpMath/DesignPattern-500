package com.book.headfirst.compoundpattern.mvc.model;

import com.book.headfirst.compoundpattern.mvc.BPMObserver;
import com.book.headfirst.compoundpattern.mvc.BeatObserver;

public interface BeatModelInterface extends BeatObservable
{
	void initialize ();
	void on ();
	void off ();
	void setBPM (int bpm);
	int getBPM ();	
}

interface BeatObservable 
{
	void registerBeatObserver (BeatObserver observer);
	void registerBPMObserver (BPMObserver observer);
	void removeBeatObserver (BeatObserver observer);
	void removeBPMObserver (BPMObserver observer);
	void notifyBeatObservers ();
	void notifyBPMObservers ();
}

