# Empower Web App - Wireframes

## App Overview
The Empower Web App is an Android application for "Empowering The Nation" - an educational platform offering various courses including First Aid, Sewing, Landscaping, Life Skills, Child Minding, Cooking, and Garden Maintenance.

## Screen Flow Diagram

```
Login Screen (MainActivity) 
    ↓
Home Screen (MainActivity2)
    ├── Courses Screen (MainActivity3)
    │   └── Course Info Screen (MainActivity6)
    ├── Calculate Fees Screen (MainActivity4)
    │   └── Payment Screen (MainActivity7)
    └── Contact Screen (MainActivity5)
```

---

## 1. Login Screen (MainActivity)

```
┌─────────────────────────────────────┐
│              LOGIN SCREEN           │
├─────────────────────────────────────┤
│                                     │
│        [LOGO/APP ICON]              │
│                                     │
│    ┌─────────────────────────────┐  │
│    │ Username                    │  │
│    │ [Text Input Field]          │  │
│    └─────────────────────────────┘  │
│                                     │
│    ┌─────────────────────────────┐  │
│    │ Password                    │  │
│    │ [Text Input Field]          │  │
│    └─────────────────────────────┘  │
│                                     │
│    ┌─────────────────────────────┐  │
│    │ Phone Number                │  │
│    │ [Text Input Field]          │  │
│    └─────────────────────────────┘  │
│                                     │
│    ┌─────────────────────────────┐  │
│    │        [SIGN IN]            │  │
│    └─────────────────────────────┘  │
│                                     │
│    ┌─────────────────────────────┐  │
│    │    [GOOGLE SIGN IN]         │  │
│    └─────────────────────────────┘  │
│                                     │
│    ┌─────────────────────────────┐  │
│    │   [FACEBOOK SIGN IN]        │  │
│    └─────────────────────────────┘  │
│                                     │
│    ┌─────────────────────────────┐  │
│    │   [LINKEDIN SIGN IN]        │  │
│    └─────────────────────────────┘  │
│                                     │
│    ┌─────────────────────────────┐  │
│    │    [GUEST SIGN IN]          │  │
│    └─────────────────────────────┘  │
│                                     │
└─────────────────────────────────────┘
```

**Features:**
- Username validation (4+ letters only)
- Password validation (8+ characters)
- Phone validation (10 digits starting with 0)
- Multiple sign-in options
- Social media authentication simulation

---

## 2. Home Screen (MainActivity2)

```
┌─────────────────────────────────────┐
│ [☰] EMPOWERING THE NATION      [≡]  │
├─────────────────────────────────────┤
│                                     │
│        Welcome to                   │
│    EMPOWERING THE NATION            │
│                                     │
│    ┌─────────────────────────────┐  │
│    │                             │  │
│    │      [HERO IMAGE/LOGO]      │  │
│    │                             │  │
│    └─────────────────────────────┘  │
│                                     │
│    ┌─────────────────────────────┐  │
│    │       [VIEW COURSES]        │  │
│    └─────────────────────────────┘  │
│                                     │
│    ┌─────────────────────────────┐  │
│    │     [CALCULATE FEES]        │  │
│    └─────────────────────────────┘  │
│                                     │
│                                     │
│                                     │
└─────────────────────────────────────┘
│ NAVIGATION DRAWER (Hidden)          │
│ ┌─────────────────────────────────┐ │
│ │ • Home                          │ │
│ │ • Courses                       │ │
│ │ │ Calculate Fees                │ │
│ │ • Contact                       │ │
│ │ • Logout                        │ │
│ └─────────────────────────────────┘ │
```

**Features:**
- Navigation drawer with hamburger menu
- Quick access to main features
- Clean, welcoming interface

---

## 3. Courses Screen (MainActivity3)

```
┌─────────────────────────────────────┐
│ [☰] COURSES                    [≡]  │
├─────────────────────────────────────┤
│                                     │
│    SIX-MONTH COURSES (R1500)       │
│                                     │
│    ┌─────────────────────────────┐  │
│    │ [IMG] First Aid             │  │
│    │       [INFO BUTTON]         │  │
│    └─────────────────────────────┘  │
│                                     │
│    ┌─────────────────────────────┐  │
│    │        [NEXT SET]           │  │
│    └─────────────────────────────┘  │
│                                     │
│    SIX-WEEK COURSES (R750)         │
│                                     │
│    ┌─────────────────────────────┐  │
│    │ [IMG] Child Minding         │  │
│    │       [INFO BUTTON]         │  │
│    └─────────────────────────────┘  │
│                                     │
│    ┌─────────────────────────────┐  │
│    │       [NEXT PACK]           │  │
│    └─────────────────────────────┘  │
│                                     │
│    ┌─────────────────────────────┐  │
│    │      [MORE INFO]            │  │
│    └─────────────────────────────┘  │
│                                     │
└─────────────────────────────────────┘
```

**Features:**
- Carousel display for courses
- Two categories: 6-month (R1500) and 6-week (R750)
- Info buttons show course details in popup
- Navigation to detailed course information

**Course Rotation:**
- **6-Month Courses:** First Aid → Sewing → Landscaping → Life Skills
- **6-Week Courses:** Child Minding → Cooking → Garden Maintenance

---

## 4. Course Information Screen (MainActivity6)

```
┌─────────────────────────────────────┐
│ [☰] COURSE INFORMATION         [≡]  │
├─────────────────────────────────────┤
│                                     │
│ 📚 Course Information               │
│ Detailed information about all      │
│ our available courses               │
│                                     │
│ 🎓 Six-Month Courses (R1500 each)  │
│                                     │
│ ┌─────────────────────────────────┐ │
│ │        [LARGE IMAGE]            │ │
│ │                                 │ │
│ │ 🚑 First Aid                    │ │
│ │ Purpose: To provide first aid   │ │
│ │ awareness and basic life support│ │
│ │                                 │ │
│ │ Content:                        │ │
│ │ • Wounds and bleeding           │ │
│ │ • Burns and fractures           │ │
│ │ • Emergency scene management    │ │
│ │ • CPR                           │ │
│ │ • Respiratory distress          │ │
│ └─────────────────────────────────┘ │
│                                     │
│ [Similar cards for other courses]   │
│                                     │
│ ⚡ Six-Week Short Courses (R750)   │
│                                     │
│ [Child Minding, Cooking, Garden     │
│  Maintenance cards...]              │
│                                     │
└─────────────────────────────────────┘
```

**Features:**
- Scrollable detailed course information
- Large images for each course
- Complete course descriptions with content breakdown
- Organized by course duration and pricing

---

## 5. Calculate Fees Screen (MainActivity4)

```
┌─────────────────────────────────────┐
│ [☰] CALCULATE FEES             [≡]  │
├─────────────────────────────────────┤
│                                     │
│    Select up to 4 courses:         │
│                                     │
│    Course 1:                        │
│    ┌─────────────────────────────┐  │
│    │ [Select a course ▼]         │  │
│    └─────────────────────────────┘  │
│                                     │
│    Course 2:                        │
│    ┌─────────────────────────────┐  │
│    │ [Select a course ▼]         │  │
│    └─────────────────────────────┘  │
│                                     │
│    Course 3:                        │
│    ┌─────────────────────────────┐  │
│    │ [Select a course ▼]         │  │
│    └─────────────────────────────┘  │
│                                     │
│    Course 4:                        │
│    ┌─────────────────────────────┐  │
│    │ [Select a course ▼]         │  │
│    └─────────────────────────────┘  │
│                                     │
│    ┌─────────────────────────────┐  │
│    │      [CALCULATE FEES]       │  │
│    └─────────────────────────────┘  │
│                                     │
│    ┌─────────────────────────────┐  │
│    │        [PURCHASE]           │  │
│    └─────────────────────────────┘  │
│                                     │
└─────────────────────────────────────┘
```

**Features:**
- 4 dropdown menus for course selection
- Duplicate course prevention
- Automatic discount calculation:
  - 1 course: 0% discount
  - 2 courses: 5% discount
  - 3 courses: 10% discount
  - 4+ courses: 15% discount
- Calculate and Purchase buttons

**Available Courses:**
- First Aid - R1500
- Sewing - R1500
- Landscaping - R1500
- Life Skills - R1500
- Child Minding - R750
- Cooking - R750
- Garden Maintenance - R750

---

## 6. Payment Screen (MainActivity7)

```
┌─────────────────────────────────────┐
│ [←] PAYMENT                         │
├─────────────────────────────────────┤
│                                     │
│ Purchase Summary:                   │
│ ┌─────────────────────────────────┐ │
│ │ You selected:                   │ │
│ │ • First Aid - R1500             │ │
│ │ • Cooking - R750                │ │
│ │                                 │ │
│ │ Subtotal: R2250                 │ │
│ │ Discount (5%): -R112            │ │
│ │ Final Total: R2138              │ │
│ └─────────────────────────────────┘ │
│                                     │
│ Payment Details:                    │
│                                     │
│ Card Number:                        │
│ ┌─────────────────────────────────┐ │
│ │ [16-digit card number]          │ │
│ └─────────────────────────────────┘ │
│                                     │
│ Expiry Month:        Expiry Year:   │
│ ┌─────────────┐    ┌─────────────┐ │
│ │ [MM]        │    │ [YYYY]      │ │
│ └─────────────┘    └─────────────┘ │
│                                     │
│ CVV:                                │
│ ┌─────────────────────────────────┐ │
│ │ [3-4 digits]                    │ │
│ └─────────────────────────────────┘ │
│                                     │
│ ┌─────────────────────────────────┐ │
│ │           [PAY NOW]             │ │
│ └─────────────────────────────────┘ │
│                                     │
└─────────────────────────────────────┘
```

**Features:**
- Purchase summary with selected courses
- Discount calculation display
- Credit card form with validation:
  - Luhn algorithm for card number validation
  - Expiry date validation
  - CVV validation (3-4 digits)
- Success confirmation dialog

---

## 7. Contact Screen (MainActivity5)

```
┌─────────────────────────────────────┐
│ [☰] CONTACT US                 [≡]  │
├─────────────────────────────────────┤
│                                     │
│ 📍 CAMPUS LOCATIONS                 │
│                                     │
│ ┌─────────────────────────────────┐ │
│ │ 🏢 CENTRAL CAMPUS               │ │
│ │ 123 Main Street, Johannesburg   │ │
│ │ Phone: (011) 123-4567           │ │
│ │                                 │ │
│ │ [📞 CALL]    [🗺️ MAP]          │ │
│ └─────────────────────────────────┘ │
│                                     │
│ ┌─────────────────────────────────┐ │
│ │ 🏢 NORTH CAMPUS                 │ │
│ │ 456 North Ave, Sandton          │ │
│ │ Phone: (011) 234-5678           │ │
│ │                                 │ │
│ │ [📞 CALL]    [🗺️ MAP]          │ │
│ └─────────────────────────────────┘ │
│                                     │
│ ┌─────────────────────────────────┐ │
│ │ 🏢 SOUTH CAMPUS                 │ │
│ │ 789 South Rd, Soweto            │ │
│ │ Phone: (011) 345-6789           │ │
│ │                                 │ │
│ │ [📞 CALL]    [🗺️ MAP]          │ │
│ └─────────────────────────────────┘ │
│                                     │
│ 📱 FOLLOW US                        │
│ [📷 Instagram] [💼 LinkedIn]       │
│ [📘 Facebook]                       │
│                                     │
└─────────────────────────────────────┘
```

**Features:**
- Three campus locations with addresses
- Direct call functionality for each campus
- Google Maps integration for directions
- Social media links (Instagram, LinkedIn, Facebook)

---

## Navigation Flow

### Main Navigation (Available from all screens via drawer):
- **Home** → MainActivity2
- **Courses** → MainActivity3
- **Calculate Fees** → MainActivity4
- **Contact** → MainActivity5
- **Logout** → MainActivity (Login)

### Screen Transitions:
1. **Login** → **Home** (after successful authentication)
2. **Home** → **Courses** → **Course Info** (via "More Info" button)
3. **Home** → **Calculate Fees** → **Payment** (via "Purchase" button)
4. **Any Screen** → **Contact** (via navigation drawer)

---

## Key UI Components

### Common Elements:
- **Header Bar**: Logo + Title + Hamburger Menu
- **Navigation Drawer**: Consistent across all main screens
- **Material Design**: Cards, buttons, and input fields
- **Color Scheme**: Light blue primary, white background

### Interactive Elements:
- **Buttons**: Primary actions (Sign In, Calculate, Purchase, etc.)
- **Dropdowns**: Course selection spinners
- **Input Fields**: Text inputs with validation
- **Cards**: Course information display
- **Dialogs**: Alerts and confirmations

### Responsive Design:
- **ScrollView**: For content that exceeds screen height
- **Constraint Layout**: Flexible positioning
- **Material Components**: Consistent styling

---

## Technical Notes

### Validation Rules:
- **Username**: 4+ letters only
- **Password**: 8+ characters
- **Phone**: 10 digits starting with 0
- **Card Number**: 13-19 digits, Luhn algorithm
- **Expiry**: Valid future date
- **CVV**: 3-4 digits

### Business Logic:
- **Course Limits**: Maximum 4 courses per purchase
- **Duplicate Prevention**: Same course can't be selected twice
- **Discount Tiers**: Progressive discounts for multiple courses
- **Course Categories**: 6-month (R1500) vs 6-week (R750)

This wireframe documentation provides a comprehensive visual guide for the Empower Web App's user interface and user experience flow.