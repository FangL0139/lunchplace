import React, { useState } from 'react';
import "./App.css";
import "./index.css"

function App() {
  const [lunchPlace, setLunchPlace] = useState('');
  const [submitRes, setSubmitRes] = useState('');
  const [getRes, setGetRes] = useState('');
  const [responseType, setResponseType] = useState('');
  const [responseTypeGet, setResponseTypeGet] = useState('');

  const handleInputChange = (e) => {
    if (e.target.name === 'lunchPlace') {
      setLunchPlace(e.target.value);
    }
  };

  const handleSubmit = () => {
    if (!lunchPlace) {
      alert('Place information incomplete');
    } else {
      // Send an HTTP POST request to the server
      fetch('http://localhost:8081/api/lunch-places', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({ "placeName": lunchPlace }),
      })
        .then((res) => {
          if (res.ok) {
            setResponseType('success');
            setSubmitRes('Lunch Place saved');
            console.log(res);
          } else {
            setResponseType('error');
            res.json().then((data) => {
              setSubmitRes(data.message);
              console.log(data);
            });
          }
        })
        .catch((error) => {
          setResponseType('error');
          setSubmitRes(error.message);
        });
    }
  };

  const handleClear = () => {
    setLunchPlace('');
    setSubmitRes('');
    setGetRes('');
  };

  const handleFindPlace = () => {
    // Send an HTTP GET request to the server
    fetch('http://localhost:8081/api/lunch-places/random')
      .then((res) => {
        console.log(res);
        if (res.ok) {
          setResponseTypeGet('success');
          res.json().then((data) =>
            setGetRes(`Your lunch place is ${data.placeName}!`));
        } else {
          setResponseTypeGet('error');
          res.json().then((data) => {
            setGetRes(data.message);
            console.log(data);
          });
        }
      })
      .catch((error) => {
        setResponseTypeGet('error');
        setGetRes(error.message);
      });
  };

  const handleDeleteAll = () => {
    fetch('http://localhost:8081/api/lunch-places/delete-all', {
      method: 'DELETE',
      headers: {
        'Content-Type': 'application/json',
      },
    })
      .then((res) => {
        if (res.ok) {
          setResponseTypeGet('success');
          setGetRes('All Places deleted');
          console.log(res);
        } else {
          setResponseTypeGet('error');
          res.json().then((data) => {
            setGetRes(data.message);
            console.log(data);
          });
        }
      })
      .catch((error) => {
        setResponseTypeGet('error');
        setGetRes(error.message);
      });
  }

  return (
    <div className='App'>
      <div className='main-heading'>
        <h1>Where to Eat Lunch</h1>
      </div>
      <div className="input-container">
        <input
          type="text"
          name="lunchPlace"
          placeholder="Lunch Place"
          value={lunchPlace}
          onChange={handleInputChange}
        />
        <button onClick={handleSubmit}>Submit</button>
        <button onClick={handleClear}>Clear</button>
      </div>
      {submitRes && <div className={responseType === 'success' ? 'success' : 'error'}>{submitRes}</div>}
      {!submitRes && <div style={{ height: '20px' }}></div>}
      <div className="input-container button-container">
        <button onClick={handleFindPlace}>Find a Place</button>
        <button onClick={handleDeleteAll} className="delete-button">Delete all places</button>
      </div>
      {getRes && <div className={responseTypeGet === 'success' ? 'success' : 'error'}>{getRes}</div>}
      {!getRes && <div style={{ height: '20px' }}></div>}
    </div>
  );
}

export default App;
