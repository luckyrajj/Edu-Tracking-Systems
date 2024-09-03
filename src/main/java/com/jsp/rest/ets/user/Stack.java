package com.jsp.rest.ets.user;

import java.util.List;

public enum Stack {
	JAVA_FULLSTACK(List.of(Subject.CORE_JAVA,
			Subject.SQL,
			Subject.JDBC,
			Subject.SERVLET,
			Subject.HIBERNATE,
			Subject.SPRING,
			Subject.SPRING_BOOT,
			Subject.HTML,
			Subject.CSS,
			Subject.JAVASCRIPT,
			Subject.REACT_JS)),
	MERN_FULLSTACK(List.of(Subject.HTML,
			Subject.CSS,
			Subject.JAVASCRIPT,
			Subject.MONGO_DB,
			Subject.EXPRESS_JS,
			Subject.REACT_JS,
			Subject.NODE_JS,
			Subject.SQL)),
	
	PYTHON_FULLSTACK(List.of(Subject.SQL,
			Subject.HTML,
			Subject.CSS,
			Subject.JAVASCRIPT,
			Subject.PYTHON));
	
	private List<Subject>subjects;

	private Stack(List<Subject> subjects) {
		this.subjects = subjects;
	}

	public List<Subject> getSubjects() {
		return subjects;
	}

}
