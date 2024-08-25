extends Node

var _firebase = null;

# Called when the node enters the scene tree for the first time.
func _ready():
	if(Engine.has_singleton("GodotFirebaseAnalytics")):
		print("GodotFirebaseAnalytics found")
		_firebase = Engine.get_singleton("GodotFirebaseAnalytics")
		
		# Firebase should be auto Initialized, if not we will try it here
		_firebase.initializeFirebase()

		# just to test
		_firebase.firstTest()
		
		_firebase.connect("on_first_test", self, "on_first_test")
	else:
		print("GodotFirebaseAnalytics Not found")

func on_first_test(reached: bool):
	print("First Test Reached to Android");

func log_event(event_name:String, data:Dictionary={}):
	if _firebase:
		_firebase.logEvent(event_name, data)

# Called every frame. 'delta' is the elapsed time since the previous frame.
#func _process(delta):
#	pass
