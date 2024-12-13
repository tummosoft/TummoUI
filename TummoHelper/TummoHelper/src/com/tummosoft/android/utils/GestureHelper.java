package com.tummosoft.android.utils;

import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.BA.Events;

@BA.ShortName("GestureHelper")
@BA.Author("Frédéric Leneuf-Magaud")
@Events(values = {
		"onTouch(Action As Int, X As Float, Y As Float, MotionEvent As Object) As Boolean",
		"onPointerDown(ptrIndex As Int, PID As Int, MotionEvent As Object)",
		"onPointerUp(ptrIndex As Int, PID As Int, MotionEvent As Object)",
		"onDown(X As Float, Y As Float, MotionEvent As Object)",
		"onSingleTapUp(X As Float, Y As Float, MotionEvent As Object)",
		"onSingleTapConfirmed(X As Float, Y As Float, MotionEvent As Object)",
		"onDoubleTap(X As Float, Y As Float, MotionEvent As Object)",
		"onShowPress(X As Float, Y As Float, MotionEvent As Object)",
		"onLongPress(X As Float, Y As Float, MotionEvent As Object)",
		"onDrag(deltaX As Float, deltaY As Float, MotionEvent As Object)",
		"onScroll(distanceX As Float, distanceY As Float, MotionEvent1 As Object, MotionEvent2 As Object)",
		"onFling(velocityX As Float, velocityY As Float, MotionEvent1 As Object, MotionEvent2 As Object)",
		"onPinchOpen(NewDistance As Float, PreviousDistance As Float, MotionEvent As Object)",
		"onPinchClose(NewDistance As Float, PreviousDistance As Float, MotionEvent As Object)",
		"onRotation(Degrees As Double, MotionEvent As Object)"
})
public class GestureHelper {
	private GestureDetector mGestureDetector;
	private BA _ba;
	private String _eventPrefix;
	private View _view;
	private float previousDistance;
	private double previousAngle;
	private boolean ignoreHeight;
	private boolean ignoreWidth;
	private float StartX, StartY;
	private boolean ResetStartPos;

	public static final int ACTION_DOWN = MotionEvent.ACTION_DOWN;
	public static final int ACTION_UP = MotionEvent.ACTION_UP;
	public static final int ACTION_MOVE = MotionEvent.ACTION_MOVE;
	public static final int ACTION_CANCEL = MotionEvent.ACTION_CANCEL;

	/**
	 *Sets the listeners for the following events:
	 *<U>onTouch</U>: notified whenever a MotionEvent occurs.
	 *  Handler: Sub MyView_onTouch(Action As Int, X As Float, Y As Float, MotionEvent As Object) As Boolean
	 *<U>onPointerDown</U>: notified when a non-primary pointer has gone down.
	 *  Handler: Sub MyView_onPointerDown(ptrIndex As Int, PID As Int, MotionEvent As Object)
	 *<U>onPointerUp</U>: notified when a non-primary pointer has gone up.
	 *  Handler: Sub MyView_onPointerUp(ptrIndex As Int, PID As Int, MotionEvent As Object)
	 *<U>onDown</U>: notified when a tap occurs with the down MotionEvent that triggered it. This will be triggered immediately for every down event. All other events should be preceded by this.
	 *  Handler: Sub MyView_onDown(X As Float, Y As Float, MotionEvent As Object)
	 *<U>onSingleTapUp</U>: notified when a tap occurs with the up MotionEvent that triggered it.
	 *  Handler: Sub MyView_onSingleTapUp(X As Float, Y As Float, MotionEvent As Object)
	 *<U>onSingleTapConfirmed</U>: notified when a single-tap occurs. Unlike onSingleTapUp, this will only be called after the detector is confident that the user's first tap is not followed by a second tap leading to a double-tap gesture.
	 *  Handler: Sub MyView_onSingleTapConfirmed(X As Float, Y As Float, MotionEvent As Object)
	 *<U>onDoubleTap</U>: notified when a double-tap occurs.
	 *  Handler: Sub MyView_onDoubleTap(X As Float, Y As Float, MotionEvent As Object)
	 *<U>onShowPress</U>: the user has performed a down MotionEvent and not performed a move or up yet. This event is commonly used to provide visual feedback to the user to let them know that their action has been recognized i.e. highlight an element.
	 *  Handler: Sub MyView_onShowPress(X As Float, Y As Float, MotionEvent As Object)
	 *<U>onLongPress</U>: notified when a long press occurs with the initial on down MotionEvent that triggered it.
	 *  Handler: Sub MyView_onLongPress(X As Float, Y As Float, MotionEvent As Object)
	 *<U>onDrag</U>: notified when a drag occurs with the move MotionEvent that triggered it. To move the dragged view, add deltaX to its Left and deltaY to its Top.
	 *  Handler: Sub MyView_onDrag(deltaX As Float, deltaY As Float, MotionEvent As Object)
	 *<U>onScroll</U>: notified when a scroll occurs with the initial on down MotionEvent and the current move MotionEvent. The distance in x and y is also supplied.
	 *  Handler: Sub MyView_onScroll(distanceX As Float, distanceY As Float, MotionEvent1 As Object, MotionEvent2 As Object)
	 *<U>onFling</U>: notified when a fling occurs with the initial on down MotionEvent and the matching up MotionEvent. The calculated velocity is supplied along the x and y axis in pixels per second.
	 *  Handler: Sub MyView_onFling(velocityX As Float, velocityY As Float, MotionEvent1 As Object, MotionEvent2 As Object)
	 *<U>onPinchOpen</U>: notified when the distance increases between two pointers.
	 *  Handler: Sub MyView_onPinchOpen(NewDistance As Float, PreviousDistance As Float, MotionEvent As Object)
	 *<U>onPinchClose</U>: notified when the distance decreases between two pointers.
	 *  Handler: Sub MyView_onPinchClose(NewDistance As Float, PreviousDistance As Float, MotionEvent As Object)
	 *<U>onRotation</U>: notified when the angle varies between two pointers. The returned angle ranges from -180 to +180 degrees.
	 *  Handler: Sub MyView_onRotation(Degrees As Double, MotionEvent As Object)
	 */
	public void SetOnGestureListener(BA ba, View view, String eventPrefix)
	{
		_ba = ba;
		_view = view;
		_eventPrefix = eventPrefix.toLowerCase();

		mGestureDetector = new GestureDetector(ba.context, new GestureListener());
		previousDistance = -1;
		previousAngle = 999;
		ignoreHeight = false;
		ignoreWidth = false;
		OnTouchListener OTL = new View.OnTouchListener()
		{
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				final int action = event.getAction();
				boolean IsHandled = true;
				if (_ba.subExists(_eventPrefix + "_ontouch")) {
					Object Result = _ba.raiseEvent(_view, _eventPrefix + "_ontouch", new Object[] {action, event.getX(), event.getY(), event});
					if (Result instanceof Boolean)
						IsHandled = (boolean) Result;
				}
				final int actionCode = action & MotionEvent.ACTION_MASK;
				if (actionCode == MotionEvent.ACTION_POINTER_DOWN && _ba.subExists(_eventPrefix + "_onpointerdown")) {
					final int actionPtrIndex = (action & MotionEvent.ACTION_POINTER_ID_MASK) >> MotionEvent.ACTION_POINTER_ID_SHIFT; 
					_ba.raiseEvent(_view, _eventPrefix + "_onpointerdown", new Object[] {actionPtrIndex, getPID(event, actionPtrIndex), event});
				}
				else if (actionCode == MotionEvent.ACTION_POINTER_UP && _ba.subExists(_eventPrefix + "_onpointerup")) {
					final int actionPtrIndex = (action & MotionEvent.ACTION_POINTER_ID_MASK) >> MotionEvent.ACTION_POINTER_ID_SHIFT;
					ResetStartPos = true;
					_ba.raiseEvent(_view, _eventPrefix + "_onpointerup", new Object[] {actionPtrIndex, getPID(event, actionPtrIndex), event});
				}
				else if (actionCode == MotionEvent.ACTION_DOWN) {
					ResetStartPos = true;
					if (_ba.subExists(_eventPrefix + "_ondown")){
						_ba.raiseEvent(_view, _eventPrefix + "_ondown", new Object[] {StartX, StartY, event});
					}
				}
				else if (actionCode == MotionEvent.ACTION_MOVE && _ba.subExists(_eventPrefix + "_ondrag")) {
					if (ResetStartPos) {
						StartX = event.getX();
						StartY = event.getY();
						ResetStartPos = false;
					}
					float deltaX = event.getX() - StartX;
					float deltaY = event.getY() - StartY;
					_ba.raiseEvent(_view, _eventPrefix + "_ondrag", new Object[] {deltaX, deltaY, event});
				}
				if (event.getPointerCount() > 1) DetectMultiTouchGestures(event);
				mGestureDetector.onTouchEvent(event);
				return IsHandled;
			}
		};
		view.setOnTouchListener(OTL);
	}

	/** Removes all gesture listeners for the currently bound view. */
	public void RemoveGestureListener()
	{
		_view.setOnTouchListener(null);
	}

	private void DetectMultiTouchGestures(MotionEvent event)
	{
		float distance;
		float distX = event.getX(1) - event.getX(0);
		float distY = event.getY(1) - event.getY(0);
		if (ignoreHeight) {
			distance = Math.abs(distX);
		} else if (ignoreWidth) {
			distance = Math.abs(distY);
		} else {
                        float s = distX * distX + distY * distY;
                        double d = s;
			distance = (float) Math.sqrt(d);
		}
		if (previousDistance != -1) {
			if (distance > previousDistance) {
				if (_ba.subExists(_eventPrefix + "_onpinchopen")) {
					_ba.raiseEvent(_view, _eventPrefix + "_onpinchopen", new Object[] {distance, previousDistance, event});
				}
			}
			else if (distance < previousDistance) {
				if (_ba.subExists(_eventPrefix + "_onpinchclose")) {
					_ba.raiseEvent(_view, _eventPrefix + "_onpinchclose", new Object[] {distance, previousDistance, event});
				}
			}
		}
		previousDistance = distance;

		if (_ba.subExists(_eventPrefix + "_onrotation")) {
			double degrees = Math.toDegrees(Math.atan2(distY, distX));
			if (previousAngle != degrees) {
				_ba.raiseEvent(_view, _eventPrefix + "_onrotation", new Object[] {degrees, event});
				previousAngle = degrees;
			}
		}
	}

	private class GestureListener extends GestureDetector.SimpleOnGestureListener
	{
		@Override
		public boolean onSingleTapUp(MotionEvent ev) {
			if (_ba.subExists(_eventPrefix + "_onsingletapup")){
				_ba.raiseEvent(_view, _eventPrefix + "_onsingletapup", new Object[] {Float.valueOf(ev.getX()), Float.valueOf(ev.getY()), ev});
			}
			return true;
		}
		@Override
		public boolean onSingleTapConfirmed(MotionEvent ev) {
			if (_ba.subExists(_eventPrefix + "_onsingletapconfirmed")){
				_ba.raiseEvent(_view, _eventPrefix + "_onsingletapconfirmed", new Object[] {Float.valueOf(ev.getX()), Float.valueOf(ev.getY()), ev});
			}
			return true;
		}
		@Override
		public boolean onDoubleTap(MotionEvent ev) {
			if (_ba.subExists(_eventPrefix + "_ondoubletap")){
				_ba.raiseEvent(_view, _eventPrefix + "_ondoubletap", new Object[] {Float.valueOf(ev.getX()), Float.valueOf(ev.getY()), ev});
			}
			return true;
		}
		@Override
		public void onShowPress(MotionEvent ev) {
			if (_ba.subExists(_eventPrefix + "_onshowpress")){
				_ba.raiseEvent(_view, _eventPrefix + "_onshowpress", new Object[] {Float.valueOf(ev.getX()), Float.valueOf(ev.getY()), ev});
			}
		}
		@Override
		public void onLongPress(MotionEvent ev) {
			if (_ba.subExists(_eventPrefix + "_onlongpress")){
				_ba.raiseEvent(_view, _eventPrefix + "_onlongpress", new Object[] {Float.valueOf(ev.getX()), Float.valueOf(ev.getY()), ev});
			}
		}
		@Override
		public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
			if (_ba.subExists(_eventPrefix + "_onscroll")){
				_ba.raiseEvent(_view, _eventPrefix + "_onscroll", new Object[] {distanceX, distanceY, e1, e2});
			}
			return true;
		}
		@Override
		public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
			if (_ba.subExists(_eventPrefix + "_onfling")){
				_ba.raiseEvent(_view, _eventPrefix + "_onfling", new Object[] {velocityX, velocityY, e1, e2});
			}
			return true;
		}
	}

	/**
	 *Sets whether longpress is enabled. If this is enabled, when a user presses and holds down, you get a longpress event and nothing further. If it's disabled, the user can press and hold down and then later move their finger and you will get scroll events. By default longpress is enabled. 
	 */
	public void EnableLongPress(boolean Enabled)
	{
		mGestureDetector.setIsLongpressEnabled(Enabled);
	}

	/**
	 *Sets whether the width is ignored when the distance is computed to detect a pinch gesture. 
	 */
	public void IgnoreWidthForPinch(boolean Ignored)
	{
		ignoreWidth = Ignored;
	}

	/**
	 *Sets whether the height is ignored when the distance is computed to detect a pinch gesture. 
	 */
	public void IgnoreHeightForPinch(boolean Ignored)
	{
		ignoreHeight = Ignored;
	}

	/**
	 *Returns the Action value from the given MotionEvent.
	 */
	public static int getAction(MotionEvent ev)
	{
		return ev.getAction();
	}

	/**
	 *Returns the PID for the given pointer index.
	 *0 is the first pointer index. 
	 */
	public static int getPID(MotionEvent ev, int ptrIndex)
	{
		return ev.getPointerId(ptrIndex);
	}

	/**
	 *Returns the pointer index for the given PID
	 *The PID (Pointer ID) is the pointer unique identifier sent back by the onPointerUp/Down functions.
	 */
	public static int getPointerIndex(MotionEvent ev, int PID)
	{
		return ev.findPointerIndex(PID);
	}

	/**
	 *Returns the Pressure value from the given MotionEvent.
	 *Set ptrIndex to 0 for the first pointer, to 1 for the second pointer, and so on.
	 */
	public static float getPressure(MotionEvent ev, int ptrIndex)
	{
		return ev.getPressure(ptrIndex);
	}

	/**
	 *Returns the Size value from the given MotionEvent.
	 *Set ptrIndex to 0 for the first pointer, to 1 for the second pointer, and so on.
	 */
	public static float getSize(MotionEvent ev, int ptrIndex)
	{
		return ev.getSize(ptrIndex);
	}

	/**
	 *Returns the X value from the given MotionEvent.
	 *Set ptrIndex to 0 for the first pointer, to 1 for the second pointer, and so on.
	 */
	public static float getX(MotionEvent ev, int ptrIndex)
	{
		return ev.getX(ptrIndex);
	}

	/**
	 *Returns the Y value from the given MotionEvent.
	 *Set ptrIndex to 0 for the first pointer, to 1 for the second pointer, and so on.
	 */
	public static float getY(MotionEvent ev, int ptrIndex)
	{
		return ev.getY(ptrIndex);
	}

	/**
	 *Returns the number of pointers contained in the given event. 
	 */
	public static int getPointerCount(MotionEvent ev)
	{
		return ev.getPointerCount();
	}

	/**
	 *Returns the number of historical points in the given event. These are movements that have occurred between this event and the previous event.
	 *This only applies to ACTION_MOVE events. All other actions will have a size of 0.
	 */
	public static int getHistorySize(MotionEvent ev)
	{
		return ev.getHistorySize();
	}

	/**
	 *Returns a historical X coordinate that occurred between this event and the previous event for the given pointer.
	 *Only applies to ACTION_MOVE events.
	 */
	public static float getHistoricalX(MotionEvent ev, int ptrIndex, int pos)
	{
		return ev.getHistoricalX(ptrIndex, pos);
	}

	/**
	 *Returns a historical Y coordinate that occurred between this event and the previous event for the given pointer.
	 *Only applies to ACTION_MOVE events.
	 */
	public static float getHistoricalY(MotionEvent ev, int ptrIndex, int pos)
	{
		return ev.getHistoricalY(ptrIndex, pos);
	}

	/**
	 *Returns a historical pressure coordinate that occurred between this event and the previous event for the given pointer.
	 *Only applies to ACTION_MOVE events.
	 */
	public static float getHistoricalPressure(MotionEvent ev, int ptrIndex, int pos)
	{
		return ev.getHistoricalPressure(ptrIndex, pos);
	}

	/**
	 *Returns a historical size coordinate that occurred between this event and the previous event for the given pointer.
	 *Only applies to ACTION_MOVE events.
	 */
	public static float getHistoricalSize(MotionEvent ev, int ptrIndex, int pos)
	{
		return ev.getHistoricalSize(ptrIndex, pos);
	}

	/**
	 *Returns the length of the major axis of an ellipse that describes the touch area at the point of contact for the given pointer index.
	 *minSDK = 9
	 */
	public static float getTouchMajor(MotionEvent ev, int ptrIndex)
	{
		return ev.getTouchMajor(ptrIndex);
	}

	/**
	 *Returns the length of the minor axis of an ellipse that describes the touch area at the point of contact for the given pointer index.
	 *minSDK = 9
	 */
	public static float getTouchMinor(MotionEvent ev, int ptrIndex)
	{
		return ev.getTouchMinor(ptrIndex);
	}

	/**
	 *Returns the precision of the X coordinates being reported. You can multiply this number with getX to find the actual hardware value of the X coordinate.
	 */
	public static float getXPrecision(MotionEvent ev)
	{
		return ev.getXPrecision();
	}

	/**
	 *Returns the precision of the Y coordinates being reported. You can multiply this number with getY to find the actual hardware value of the Y coordinate.
	 */
	public static float getYPrecision(MotionEvent ev)
	{
		return ev.getYPrecision();
	}

	/**
	 *Creates a MotionEvent to be used with PassTouchEventTo.
	 *@param downTime The time (in ms) when the user originally pressed down to start a stream of position events.
	 *@param eventTime The time (in ms) when this specific event was generated.
	 *@param Action The kind of action being performed. One of the ACTION constants.
	 *@param X The X coordinate of this event.
	 *@param Y The Y coordinate of this event.
	 */
	public static Object CreateMotionEvent(long downTime, long eventTime, int Action, float X, float Y)
	{
		return MotionEvent.obtain(downTime, eventTime, Action, X, Y, 0);
	}

	/**
	 *Passes the given motion event down to the target view.
	 *Returns True if the event was handled by the view, False otherwise.
	 */
	public static boolean PassTouchEventTo(MotionEvent ev, View Target)
	{
		return Target.dispatchTouchEvent(ev);
	}
}