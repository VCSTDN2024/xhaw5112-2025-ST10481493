let storedTotal = localStorage.getItem('finalTotal');
if (storedTotal) {
  total = parseFloat(storedTotal);
  document.getElementById('totalAmount').textContent = total.toFixed(2);
}
