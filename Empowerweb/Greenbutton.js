document.addEventListener('DOMContentLoaded', () => {
  const form = document.getElementById('enrollForm');
  const msg  = document.getElementById('submit-message');
  if (!form || !msg) return;

  form.addEventListener('submit', (e) => {
    e.preventDefault();
    msg.style.display = 'block';
    form.reset();
  });
});
