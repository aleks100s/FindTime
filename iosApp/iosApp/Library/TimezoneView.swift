import SwiftUI
import shared

struct TimezoneView: View {
	@EnvironmentObject private var timezoneItems: TimezoneItems
	private var timezoneHelper = TimeZoneHelperImpl()
	@State private var currentDate = Date()
	let timer = Timer.publish(every: 1000, on: .main, in: .common).autoconnect()
	@State private var showTimezoneDialog = false
	
	var body: some View {
		NavigationView {
			VStack {
				TimeCard(
					timezone: timezoneHelper.currentTimeZone(),
					time: DateFormatter.short.string(from: currentDate),
					date: DateFormatter.long.string(from: currentDate)
				)
				
				Spacer()
				
				List {
					ForEach(Array(timezoneItems.selectedTimezones), id: \.self) { timezone in
						NumberTimeCard(
							timezone: timezone,
							time: timezoneHelper.getTime(timeZoneId: timezone),
							hours: "\(timezoneHelper.hoursFromTimeZone(otherTimeZoneId: timezone)) hours from local",
							date: timezoneHelper.getDate(timeZoneId: timezone)
						)
						.withListModifier()
					}
					.onDelete(perform: deleteItems)
				}
				.listStyle(.plain)
				
				Spacer()
			}
			.onReceive(timer) { date in
				currentDate = date
			}
			.navigationTitle("World Clocks")
			.toolbar {
				ToolbarItem(placement: .navigationBarTrailing) {
					Button(action: {
						showTimezoneDialog = true
					}) {
						Image(systemName: "plus")
							.frame(alignment: .trailing)
							.foregroundColor(.black)
					}
				}
			}
		}
		.fullScreenCover(isPresented: $showTimezoneDialog) {
			TimezoneDialog()
				.environmentObject(timezoneItems)
		}
	}
	
	func deleteItems(at offsets: IndexSet) {
		let timezoneArray = Array(timezoneItems.selectedTimezones)
		for index in offsets {
			timezoneItems.selectedTimezones.remove(timezoneArray[index])
		}
	}
}

