// Calculater.js
document.addEventListener('DOMContentLoaded', () => {
  const ids = ['course1', 'course2', 'course3', 'course4'];
  const totalEl = document.getElementById('total');
  const discountEl = document.getElementById('discount');
  const paymentButton = document.getElementById('paymentButton');

  function calculateTotal() {
    const prices = ids.map(id => Number(document.getElementById(id).value || 0));
    const course1Selected = prices[0] > 0;

    if (!course1Selected) {
      totalEl.textContent = 'R0.00';
      discountEl.textContent = 'Please select Course 1 to begin.';
      paymentButton.style.display = 'none';
      localStorage.removeItem('finalTotal');
      localStorage.removeItem('selectedCourses');
      return;
    }

    const selectedCourses = [];
    ids.forEach(id => {
      const select = document.getElementById(id);
      const text = select.options[select.selectedIndex].text;
      if (Number(select.value) > 0) selectedCourses.push(text);
    });

    const subtotal = prices.reduce((a, b) => a + b, 0);
    const selectedCount = prices.filter(p => p > 0).length;

    let discountRate = 0;
    if (selectedCount === 2) discountRate = 0.05;
    else if (selectedCount === 3) discountRate = 0.10;
    else if (selectedCount > 3) discountRate = 0.15;

    const discountAmount = subtotal * discountRate;
    const total = subtotal - discountAmount;

    totalEl.textContent = 'R' + total.toFixed(2);
    discountEl.textContent = discountRate > 0
      ? `Discount applied: ${discountRate * 100}%`
      : '';

    paymentButton.style.display = total > 0 ? 'block' : 'none';

    // Save data to localStorage for payment page
    localStorage.setItem('finalTotal', total.toFixed(2));
    localStorage.setItem('selectedCourses', JSON.stringify(selectedCourses));
  }

  ids.forEach(id => {
    document.getElementById(id).addEventListener('change', calculateTotal);
  });

  paymentButton.addEventListener('click', () => {
    window.location.href = 'payment.html';
  });
});
