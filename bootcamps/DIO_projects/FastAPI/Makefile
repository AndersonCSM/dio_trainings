run:
	@uvicorn workout_api.main:app --reload

create-migrations:
	alembic revision --autogenerate -m "init-db

run-migrations:
	@PYTHONPATH=$PYTHONPATH:$(pwd)alembic upgrade head

