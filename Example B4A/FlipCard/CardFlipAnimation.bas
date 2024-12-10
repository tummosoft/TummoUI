B4A=true
Group=Default Group
ModulesStructureVersion=1
Type=Class
Version=13
@EndOfDesignText@
Sub Class_Globals
	Private cameraView As CameraHelper
	Private centerX As Float
	Private centerY As Float
	Private isReversed As Boolean
	Dim anim As AnimationHelper
	Dim degrees As Float
	Dim matrix As xMatrix
End Sub

Public Sub Initialize(X As Float, Y As Float, Reversed As Boolean)
	cameraView.initialize("cameraView")
	centerX = X / 2.0f
	centerY = Y / 2.0f
	
	isReversed = Reversed
	anim.initialize("anim")		
End Sub

Public Sub GetAimation() As AnimationHelper
	Return anim
End Sub

Sub anim_applyTransformation(interpolatedTime As Float, t As xTransformation)
	degrees = 180 * interpolatedTime
	matrix = t.Matrix
	
	cameraView.save()
	If (isReversed) Then
		cameraView.rotateX(-degrees)
	Else
		cameraView.rotateY(degrees)
	End If
	
	cameraView.getMatrix(matrix)
	cameraView.restore()

	matrix.preTranslate(-centerX, -centerY)
	matrix.postTranslate(centerX, centerY)
	
	Log(degrees)
	
End Sub

