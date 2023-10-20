import React, { useState } from 'react';
import "./App.css";
import "./index.css"

function App() {
  const [lunchPlace, setLunchPlace] = useState('');
  const [submitRes, setSubmitRes] = useState('');
  const [getRes, setGetRes] = useState('');

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
        // .then((res) => res.json())
        .then((res) => {
          if (res.ok) {
            setSubmitRes('Lunch Place saved');
            console.log(res);
          } else {
            // return submitRes.text().then((error) => {
            res.json().then((data) => {
              setSubmitRes(data.message);
              console.log(data);
            });
            // });
          }
        })
        .catch((error) => {
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
      // .then((res) => res.json())
      .then((res) => {
        console.log(res);
        if (res.ok) {
          res.json().then((data) =>
            setGetRes(`Place Name: ${data.placeName}`));
        } else {
          res.json().then((data) => {
            setGetRes(data.message);
            console.log(data);
          });
        }
      })
      .catch((error) => {
        setGetRes(error.message);
      });
  };

  const handfleDeleteAll = ()=>{
    fetch('http://localhost:8081/api/lunch-places/delete-all', {
      method: 'DELETE',
      headers: {
        'Content-Type': 'application/json',
      },
    })
      // .then((res) => res.json())
      .then((res) => {
        if (res.ok) {
          setGetRes('All Places deleted');
          console.log(res);
        } else {
          // return submitRes.text().then((error) => {
          res.json().then((data) => {
            setGetRes(data.message);
            console.log(data);
          });
          // });
        }
      })
      .catch((error) => {
        setGetRes(error.message);
      });
  }

  return (
    <div className='App'>
      <h1 style={{ fontWeight: 'bold' }}>Where to Eat Lunch</h1>
      <div>
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
      <div style={{ color: 'green' }}>{submitRes}</div>
      <button onClick={handleFindPlace}>Find a Place</button>
      <button onClick={handfleDeleteAll}>Delete all places</button>
      <div style={{ color: 'green' }}>{getRes}</div>
    </div>
  );
}

export default App;
