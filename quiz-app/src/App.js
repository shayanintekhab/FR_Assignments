import React, { useState, useEffect } from 'react';
import axios from 'axios';

export default function App() {
  const [stage, setStage] = useState('quiz');
  const [questions, setQuestions] = useState([]);
  const [answers, setAnswers] = useState({});

  useEffect(() => {
    axios.get('http://localhost:3000/questions')
      .then(res => {
        const shuffled = res.data.sort(() => 0.5 - Math.random());
        setQuestions(shuffled.slice(0, 10));
      });
  }, []);

  const handleSelect = (id, option) => {
    setAnswers(prev => ({ ...prev, [id]: option }));
  };

  const correctCount = questions.filter(q => answers[q.id] === q.answer).length;

  return (
    <div className="app-container">
      {stage === 'quiz' && (
        <>
          <h2>Quiz Time!</h2>
          {questions.map(q => (
            <div key={q.id} className="question-card">
              <h5>{q.question}</h5>
              {q.options.map(opt => (
                <label key={opt} className="option">
                  <input
                    type="radio"
                    name={String(q.id)}
                    onChange={() => handleSelect(q.id, opt)}
                  />{opt}
                </label>
              ))}
            </div>
          ))}
          <div className="button-group">
            <button
              className="btn-primary"
              onClick={() => setStage('review')}
              disabled={Object.keys(answers).length < questions.length}
            >Review Answers</button>
          </div>
        </>
      )}

      {stage === 'review' && (
        <>
          <h2>Review Your Answers</h2>
          {questions.map(q => (
            <div key={q.id} className="review-item">
              <p><strong>{q.question}</strong></p>
              <p>Your: <span style={{color: answers[q.id] === q.answer ? '#28a745' : '#dc3545'}}>{answers[q.id]}</span></p>
              <p>Correct: <span style={{color: '#28a745'}}>{q.answer}</span></p>
            </div>
          ))}
          <div className="button-group">
            <button className="btn-primary" onClick={() => setStage('result')}>See Result</button>
          </div>
        </>
      )}

      {stage === 'result' && (
        <div className="result">
          <h2>Your Score</h2>
          <h3>{correctCount} / {questions.length}</h3>
          <button className="btn-primary" onClick={() => window.location.reload()}>Try Again</button>
        </div>
      )}
    </div>
  );
}
